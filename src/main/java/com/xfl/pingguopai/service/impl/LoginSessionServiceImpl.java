package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.dao.LoginSessionMapper;
import com.xfl.pingguopai.model.LoginSession;
import com.xfl.pingguopai.service.CacheHelper;
import com.xfl.pingguopai.service.LoginSessionService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by timely
 */
@Service
@Transactional
public class LoginSessionServiceImpl extends AbstractServiceImpl<LoginSession, Long> implements LoginSessionService {
    @Resource
    private LoginSessionMapper loginSessionMapper;

    @Autowired
    private CacheHelper cacheHelper;

    @Override
    public int deleteByToken(String token) {
        Example example = new Example(LoginSession.class);
        example.createCriteria().andCondition("user_token", token);
        List<LoginSession> loginSessionList = loginSessionMapper.selectByCondition(example);
        int rtn = 0;
        for (LoginSession loginSession : loginSessionList) {
            rtn ++;
            loginSessionMapper.deleteByPrimaryKey(loginSession.getId());
        }
        return rtn;
    }
}
