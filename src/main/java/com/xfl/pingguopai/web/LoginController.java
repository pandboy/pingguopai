package com.xfl.pingguopai.web;

import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultGenerator;
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
public class LoginController {
    @Resource
    private UserService userService;

    @PostMapping("/check")
    public Result check(@RequestParam String username, @RequestParam String password) {
        String token = userService.loginCheck(username, password);
        return ResultGenerator.genSuccessResult(token);
    }

    @PostMapping("/logout")
    public Result delete(@RequestParam String token) {
        userService.deleteByToken(token);
        return ResultGenerator.genSuccessResult();
    }
}
