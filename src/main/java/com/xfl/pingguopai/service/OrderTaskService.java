package com.xfl.pingguopai.service;
import com.xfl.pingguopai.model.OrderTask;
import com.xfl.pingguopai.common.AbstractService;
import com.xfl.pingguopai.model.User;


/**
 * Created by timely
 */
public interface OrderTaskService extends AbstractService<OrderTask, Long> {

    User findExecutorByOrderId(Long orderId);
}
