package com.xfl.pingguopai.service.impl;

import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import com.xfl.pingguopai.common.ServiceException;
import com.xfl.pingguopai.dao.UserMapper;
import com.xfl.pingguopai.helper.OrderSO;
import com.xfl.pingguopai.helper.enums.OrderStatus;
import com.xfl.pingguopai.model.Authority;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.model.UserAuthority;
import com.xfl.pingguopai.service.*;
import com.xfl.pingguopai.helper.enums.UserType;
import com.xfl.pingguopai.util.CollectionUtil;
import com.xfl.pingguopai.vo.UserVO;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * Created by timely
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User, Long>
        implements UserService, UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${security.salt}")
    private String salt;

    @Value("${security.secretkey}")
    private String secretkey;
    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserAuthorityService userAuthorityService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("[UserService->loadUserByUsername] start username is {} ...", username);

        User user = findBy("username", username);
        if (user == null) {
            logger.warn("[UserService->loadUserByUsername]  {} not find", username);
            throw new UsernameNotFoundException(String.format("查无此用户 '%s'.", username));
        } else {
            user.setAuthorities(getAuthByUser(user.getId()));
            logger.warn("[UserService->loadUserByUsername] end username is {}...", username);
            return user;
        }
    }



   protected List<Authority> getAuthByUser(long userId) {
       Condition condition = new Condition(UserAuthority.class);
       condition.createCriteria().andEqualTo("userId", userId);
       List<UserAuthority> userAuthorities = userAuthorityService.findByCondition(condition);
       List<Authority> authorities = new ArrayList<>(userAuthorities.size());
       userAuthorities.stream().forEach(userAuthority -> {
           authorities.add(authorityService.findById(userAuthority.getAuthorityId()));
       });
       return authorities;
    }

    @Override
    public int save(User model) {
        logger.info("[UserService->save] start username is {} ...", model.getUsername());
        Assert.hasText(model.getUsername(), "请输入用户名");
        if (findBy("username", model.getUsername()) != null) {
            throw new ServiceException("用户名不允许重复");
        }
        String password = model.getPassword();
        model.setVersion(0);
        model.setUserType(UserType.USER.value());
        String mysalt = new BCryptPasswordEncoder().encode(password);
        model.setPassword(BCrypt.hashpw(password, mysalt));
        int rtn = super.save(model);
        userAuthorityService.grantNormalAuth(model.getId());
        logger.info("[UserService->save] end username is {} ...", model.getUsername());
        return rtn;
    }

    @Override
    public int update(User model) {
        logger.info("[UserService->update] start username is {} ...", model.getUsername());

        model.setPassword(null); //增量更新，空的不更新

        int rtn = super.update(model);
        logger.info("[UserService->update] end username is {} ...", model.getUsername());
        return rtn;
    }

    @Override
    public PageInfo<UserVO> getUserOrderDistanceList(OrderSO so) {
        logger.info("[UserService->getUserOrderDistanceList] start ...");

        Condition condition = new Condition(Order.class);
        condition.createCriteria()
                .andBetween("createTime", so.getBeginTime(), so.getEndTime())
                .andEqualTo("orderStatus", OrderStatus.EXECUTED.getCode());
        List<Order> allOrder = orderService.findByCondition(condition);
        orderService.joinUser(allOrder);
        Map<User, List<Order>>
                userOrderListMap = allOrder.stream()
                .collect(Collectors.
                        groupingBy( order -> order.getUser(),
                                Collectors.mapping(order -> order,
                                        Collectors.toList())));
        List<UserVO> userVOs = new ArrayList<>();
        for (Map.Entry<User, List<Order>> entry : userOrderListMap.entrySet()) {
            User user = entry.getKey();
            UserVO userVO = dozerUtil.convert(user, UserVO.class);
            double totalDistance = 0.0D;
            for (Order order : entry.getValue()) {
                totalDistance = totalDistance + order.getDistance();
            }
            userVO.setTotalDistance(totalDistance);
            userVOs.add(userVO);
        }
        logger.info("[UserService->getUserOrderDistanceList] end ...");

        return new PageInfo<>(userVOs);
    }
}
