package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.common.AbstractServiceImpl;
import com.xfl.pingguopai.common.ServiceException;
import com.xfl.pingguopai.dao.LoginSessionMapper;
import com.xfl.pingguopai.dao.UserMapper;
import com.xfl.pingguopai.model.Authority;
import com.xfl.pingguopai.model.LoginSession;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.model.UserAuthority;
import com.xfl.pingguopai.service.AuthorityService;
import com.xfl.pingguopai.service.CacheHelper;
import com.xfl.pingguopai.service.UserAuthorityService;
import com.xfl.pingguopai.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by timely
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User, Long>
        implements UserService, UserDetailsService {
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
    private CacheHelper cacheHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findBy("username", username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("查无此用户 '%s'.", username));
        } else {
            user.setAuthorities(getAuthByUser(user.getId()));
            return user;
        }
    }

   protected List<Authority> getAuthByUser(long userId) {
       Condition condition = new Condition(UserAuthority.class);
       condition.createCriteria().andEqualTo("userId", userId);
       List<UserAuthority> userAuthorities = userAuthorityService.findByCondition(condition);
       List<Authority> authorities = new ArrayList<>(userAuthorities.size());
       userAuthorities.stream().forEach(userAuthority -> {
           authorities.add(authorityService.findById(userAuthority.getId()));
       });
       return authorities;
    }

    @Override
    public void save(User model) {
        String password = model.getPassword();
        model.setVersion(0);
        model.setPassword(BCrypt.hashpw(password, salt));
        super.save(model);
    }

    @Override
    public void update(User model) {
        model.setPassword(null); //增量更新，空的不更新
        super.update(model);
    }
}
