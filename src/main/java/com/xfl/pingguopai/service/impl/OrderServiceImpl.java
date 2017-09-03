package com.xfl.pingguopai.service.impl;

import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.dao.OrderMapper;
import com.xfl.pingguopai.dao.OrderTaskMapper;
import com.xfl.pingguopai.helper.OrderSO;
import com.xfl.pingguopai.helper.enums.OrderStatus;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.model.OrderTask;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.service.OrderService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import com.xfl.pingguopai.service.OrderTaskService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by xfl
 */
@Service
@Transactional
public class OrderServiceImpl extends AbstractServiceImpl<Order, Long> implements OrderService {
    @Resource
    private OrderMapper tOrderMapper;

    @Autowired
    private OrderTaskService orderTaskService;


    @Override
    public int assign(Long orderId, Long userId) {
        OrderTask orderTask = new OrderTask();
        orderTask.setOrderId(orderId);
        orderTask.setUserId(userId);
        return orderTaskService.save(orderTask);
    }

    @Override
    public PageInfo<Order> getOrderList(OrderSO so) {
        Condition condition = new Condition(Order.class);
        condition.createCriteria().andBetween("createTime", so.getBeginTime(), so.getEndTime());
        PageInfo<Order> pageList = selectPage(condition, so);
        List<Order> orderList = pageList.getList();
        for (Order order : orderList) {
            User executor = orderTaskService.findExecutorByOrderId(order.getId());
        }
        return pageList;
    }

    @Override
    public int saveAndAssign(Order order, Long userId) {
        Assert.notNull(userId, "用户不允许空");
        order.setOrderStatus(OrderStatus.UNEXECUTED.getCode());
        save(order);
        OrderTask orderTask = new OrderTask();
        orderTask.setOrderId(order.getId());
        orderTask.setUserId(userId);
        orderTask.setTaskStatus(OrderStatus.UNEXECUTED.getCode());
        return orderTaskService.save(orderTask);
    }

    @Override
    public int excute(Long orderId, Integer orderStatus) {
        Assert.notNull(orderId, "必须传入用户id");
        findById(orderId);
        return 0;
    }
}
