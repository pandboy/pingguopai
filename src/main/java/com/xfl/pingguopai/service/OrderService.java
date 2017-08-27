package com.xfl.pingguopai.service;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.common.AbstractService;


/**
 * Created by xfl
 */
public interface OrderService extends AbstractService<Order, Long> {

    int assign(Long orderId, Long userId);
}
