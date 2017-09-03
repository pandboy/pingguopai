package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.dao.OrderTaskMapper;
import com.xfl.pingguopai.model.OrderTask;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.service.OrderTaskService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import com.xfl.pingguopai.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by timely
 */
@Service
@Transactional
public class OrderTaskServiceImpl extends AbstractServiceImpl<OrderTask, Long> implements OrderTaskService {
    @Resource
    private OrderTaskMapper tOrderTaskMapper;
    @Resource
    private UserService userService;

    @Override
    public User findExecutorByOrderId(Long orderId) {
        OrderTask orderTask = tOrderTaskMapper.selectByPrimaryKey(orderId);
        User user = null;
        if (orderTask != null) {
            user = userService.findById(orderTask.getUserId());
        }
        return user;
    }
}
