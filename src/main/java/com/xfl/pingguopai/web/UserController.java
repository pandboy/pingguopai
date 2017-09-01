package com.xfl.pingguopai.web;
import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultGenerator;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.model.UserGps;
import com.xfl.pingguopai.service.UserGpsService;
import com.xfl.pingguopai.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by timely
*/
@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserGpsService userGpsService;

    @PostMapping("/add")
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "0") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    @PostMapping("/gps/latest")
    public Result getLatestLocation(@RequestParam Integer userId) {
        UserGps userGps = userGpsService.findLatestGPS(userId);
        return ResultGenerator.genSuccessResult(userGps);
    }
}
