package com.xfl.pingguopai.web;
import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultGenerator;
import com.xfl.pingguopai.helper.OrderSO;
import com.xfl.pingguopai.helper.PageList;
import com.xfl.pingguopai.helper.enums.UserType;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.model.UserGps;
import com.xfl.pingguopai.service.UserGpsService;
import com.xfl.pingguopai.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.vo.UserVO;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
* Created by timely
*/
@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private UserGpsService userGpsService;

    @GetMapping("/whoami")
   // @PreAuthorize("hasRole('USER', 'ADMIN')")
    public Result user() {
        return ResultGenerator.genSuccessResult((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public Result delete(@RequestParam Long id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/listNormalAll")
    @PreAuthorize("hasRole('ADMIN')")
    public Result list(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "0") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.findAll();
        list = list.stream().filter(user -> user.getUserType() != UserType.ADMIN.value())
        .collect(Collectors.toList());
        return ResultGenerator.genSuccessResult(list);
    }
    @PostMapping("/gps/latest")
    public Result getLatestLocation(@RequestParam Integer userId) {
        UserGps userGps = userGpsService.findLatestGPS(userId);
        return ResultGenerator.genSuccessResult(userGps);
    }

    @PostMapping("/order/distance")
    @PreAuthorize("hasRole('ADMIN')")
    public Result getSalary(OrderSO so) {
        PageList<UserVO> pageInfo = userService.getUserOrderDistanceList(so);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
