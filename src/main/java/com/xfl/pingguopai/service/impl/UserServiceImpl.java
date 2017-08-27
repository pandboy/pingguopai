package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.common.AbstractServiceImpl;
import com.xfl.pingguopai.common.MyConstant;
import com.xfl.pingguopai.common.ServiceException;
import com.xfl.pingguopai.dao.LoginSessionMapper;
import com.xfl.pingguopai.dao.UserMapper;
import com.xfl.pingguopai.model.LoginSession;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.service.CacheHelper;
import com.xfl.pingguopai.service.UserService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;


/**
 * Created by timely
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginSessionMapper loginSessionMapper;

    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public String loginCheck(String username, String password) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName", username);
        User user = findBy("userName", username);
        if (user == null) {
            throw new ServiceException("查无次用户");
        }
        String token = null;
        if (StringUtils.equals(password, user.getPassword())) {
            token = Md5Crypt.md5Crypt(password.getBytes(), MyConstant.SALT);

            //保存到数据库
            LoginSession loginSession = new LoginSession();
            loginSession.setUserToken(token);
            DateTime dt = new DateTime();
            loginSession.setExpireDate(dt.plusDays(1).toDate());
            loginSessionMapper.insert(loginSession);
            cacheHelper.saveSession(token, user);
        }
        return token;
    }

    @Override
    public void update(User model) {
        User localUser = findById(model.getId());
        model.setPassword(localUser.getPassword());
        super.update(model);
    }
}
