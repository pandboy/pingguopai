package com.xfl.pingguopai.web;
import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultGenerator;
import com.xfl.pingguopai.service.LoginSessionService;
import com.xfl.pingguopai.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* Created by timely
*/
@RestController
@RequestMapping("/login")
public class LoginSessionController {
    @Resource
    private LoginSessionService loginSessionService;
    @Resource
    private UserService userService;

    @PostMapping("/check")
    public Result check(@RequestParam String username, @RequestParam String password) {
        userService.loginCheck(username, password);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/logout")
    public Result delete(@RequestParam String token) {
        loginSessionService.deleteByToken(token);
        return ResultGenerator.genSuccessResult();
    }
}
