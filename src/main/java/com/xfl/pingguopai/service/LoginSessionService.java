package com.xfl.pingguopai.service;
import com.xfl.pingguopai.model.LoginSession;
import com.xfl.pingguopai.common.AbstractService;


/**
 * Created by timely
 */
public interface LoginSessionService extends AbstractService<LoginSession, Long> {

    int deleteByToken(String token);
}
