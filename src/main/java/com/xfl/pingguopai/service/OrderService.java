package com.xfl.pingguopai.service;
import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.helper.OrderSO;
import com.xfl.pingguopai.helper.PageList;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.common.AbstractService;

import java.util.List;


/**
 * Created by xfl
 */
public interface OrderService extends AbstractService<Order, Long> {

    int assign(Long orderId, Long userId);

    PageList<Order> getOrderList(OrderSO so);

    int saveAndAssign(Order order, Long userId);

    int excute(Long orderId, Integer orderStatus);

    List<Order> joinUser(List<Order> orderList);

    List<Order> getMyOrders(OrderSO so);
}
