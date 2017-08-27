package com.xfl.pingguopai.service.impl;

import com.xfl.pingguopai.dao.OrderTaskMapper;
import com.xfl.pingguopai.model.OrderTask;
import com.xfl.pingguopai.service.OrderTaskService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
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

}
