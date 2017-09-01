package com.xfl.pingguopai.service;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.common.AbstractService;


/**
 * Created by timely
 */
public interface UserService extends AbstractService<User, Long> {

    String loginCheck(String username, String password);

    void deleteByToken(String token);
}
