package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.dao.UserAuthorityMapper;
import com.xfl.pingguopai.model.UserAuthority;
import com.xfl.pingguopai.service.AuthorityService;
import com.xfl.pingguopai.service.UserAuthorityService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/09/01.
 */
@Service
@Transactional
public class UserAuthorityServiceImpl extends AbstractServiceImpl<UserAuthority, Long> implements UserAuthorityService {
    @Resource
    private UserAuthorityMapper tUserAuthorityMapper;

    @Resource
    private AuthorityService authorityService;

    @Override
    public void grantNormalAuth(Long userId) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(userId);
        userAuthority.setAuthorityId(authorityService.findBy("name", "ROLE_USER").getId());
        tUserAuthorityMapper.insert(userAuthority);
    }
}
