package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.dao.AuthorityMapper;
import com.xfl.pingguopai.model.Authority;
import com.xfl.pingguopai.service.AuthorityService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/09/01.
 */
@Service
@Transactional
public class AuthorityServiceImpl extends AbstractServiceImpl<Authority, Long> implements AuthorityService {
    @Resource
    private AuthorityMapper tAuthorityMapper;

}
