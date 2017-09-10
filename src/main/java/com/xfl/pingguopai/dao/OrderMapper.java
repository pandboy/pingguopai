package com.xfl.pingguopai.dao;

import com.xfl.pingguopai.common.Mapper;
import com.xfl.pingguopai.helper.OrderSO;
import com.xfl.pingguopai.model.Order;

import java.util.List;


public interface OrderMapper extends Mapper<Order> {
    List<Order> getMyOrders(OrderSO so);
}