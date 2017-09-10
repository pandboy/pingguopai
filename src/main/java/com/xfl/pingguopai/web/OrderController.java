package com.xfl.pingguopai.web;

import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultGenerator;
import com.xfl.pingguopai.helper.PageList;
import com.xfl.pingguopai.helper.SecurityContextUtil;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.service.OrderService;
import com.xfl.pingguopai.helper.OrderSO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by timely
*/
@RestController
@RequestMapping("/auth/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Resource
    private OrderService orderService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(Order order, @RequestParam Long userId) {
        orderService.saveAndAssign(order, userId);
        return ResultGenerator.genSuccessResult(order);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public Result delete(@RequestParam Long id) {
        orderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

  //  @PostMapping("/update")
    //@PreAuthorize("hasRole('ADMIN')")
    public Result update(Order order) {
        orderService.update(order);
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public Result list(OrderSO so) {
        PageList<Order> pageInfo = orderService.getOrderList(so);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    @PostMapping("/myOrders")
    @PreAuthorize("hasRole('USER')")
    public Result getMyOrders(OrderSO so) {
        User user = SecurityContextUtil.logingUser();
        so.setUserId(user.getId());
        List<Order> list = orderService.getMyOrders(so);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/execute")
    @PreAuthorize("hasRole('USER')")
    public Result execute(@RequestParam Long orderId, @RequestParam Integer orderStatus) {
        int rtn = orderService.excute(orderId, orderStatus);

        return rtn == 1 ? ResultGenerator.genSuccessResult() : ResultGenerator.genFailResult("分配失败");
    }
}
