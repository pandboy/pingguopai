package com.xfl.pingguopai.web;
import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultGenerator;
import com.xfl.pingguopai.model.UserGps;
import com.xfl.pingguopai.service.UserGpsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by timely
*/
@RestController
@RequestMapping("/auth/user/gps")
public class UserGpsController {
    @Resource
    private UserGpsService userGpsService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public Result add(UserGps userGps) {
        userGpsService.save(userGps);
        return ResultGenerator.genSuccessResult();
    }
}
