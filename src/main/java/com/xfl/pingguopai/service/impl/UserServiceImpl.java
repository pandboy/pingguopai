package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.common.AbstractServiceImpl;
import com.xfl.pingguopai.common.ServiceException;
import com.xfl.pingguopai.dao.LoginSessionMapper;
import com.xfl.pingguopai.dao.UserMapper;
import com.xfl.pingguopai.model.LoginSession;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.service.CacheHelper;
import com.xfl.pingguopai.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${security.salt}")
    private String salt;

    @Value("${security.secretkey}")
    private String secretkey;
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginSessionMapper loginSessionMapper;

    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public void save(User model) {
        String password = model.getPassword();
        if (model.getUserType() == null) {
            model.setUserType(3);//普通用户
        }
        model.setVersion(0);
        model.setPassword(Md5Crypt.md5Crypt(password.getBytes(), salt));
        super.save(model);
    }

    @Override
    public String loginCheck(String username, String password) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName", username);
        User user = findBy("userName", username);
        if (user == null) {
            throw new ServiceException("查无此用户");
        }
        String jwtToken = null;
        String encryptPassword =  Md5Crypt.md5Crypt(password.getBytes(), salt);
        if (StringUtils.equals(encryptPassword, user.getPassword())) {
            String roleType = user.getUserType() == 1 ? "admin" : "normal";
            jwtToken = Jwts.builder().setSubject(username).claim("roles", roleType)
                    .setIssuedAt(LocalDate.now().toDate())
                    .signWith(SignatureAlgorithm.HS256, secretkey).compact();
            cacheHelper.saveSession(jwtToken, user);
            //return jwtToken;
            //保存到数据库
           /* LoginSession loginSession = new LoginSession();
            loginSession.setUserToken(jwtToken);
            DateTime dt = new DateTime();
            loginSession.setExpireDate(dt.plusDays(1).toDate());
            loginSessionMapper.insert(loginSession);*/
        } else {
            throw new ServiceException("密码错误");
        }
        return jwtToken;
    }

    @Override
    public void update(User model) {
        model.setPassword(null); //增量更新，空的不更新
        super.update(model);
    }

    @Override
    public void deleteByToken(String token) {
        cacheHelper.removeSession(token);
    }
}
