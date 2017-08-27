package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.dao.OrderMapper;
import com.xfl.pingguopai.dao.OrderTaskMapper;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.model.OrderTask;
import com.xfl.pingguopai.service.OrderService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xfl
 */
@Service
@Transactional
public class OrderServiceImpl extends AbstractServiceImpl<Order, Long> implements OrderService {
    @Resource
    private OrderMapper tOrderMapper;

    @Autowired
    private OrderTaskMapper orderTaskMapper;

    @Override
    public int assign(Long orderId, Long userId) {
        OrderTask orderTask = new OrderTask();
        orderTask.setOrderId(orderId);
        orderTask.setUserId(userId);
        return orderTaskMapper.insert(orderTask);
    }
}
