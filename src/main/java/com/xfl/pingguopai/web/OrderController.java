package com.xfl.pingguopai.web;

import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.common.Result;
import com.xfl.pingguopai.common.ResultGenerator;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.service.OrderService;
import com.xfl.pingguopai.helper.OrderSO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by timely
*/
@RestController
@RequestMapping("/auth/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Result add(@RequestBody Order order, @RequestParam Long userId) {
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
    @PreAuthorize("hasRole('USER')")
    public Result list(OrderSO so) {
        PageInfo<Order> pageInfo = orderService.getOrderList(so);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/execute")
    @PreAuthorize("hasRole('ADMIN', 'USER')")
    public Result execute(@RequestParam Long orderId, @RequestParam Integer orderStatus) {
        int rtn = orderService.excute(orderId, orderStatus);

        return rtn == 1 ? ResultGenerator.genSuccessResult() : ResultGenerator.genFailResult("分配失败");
    }
}
