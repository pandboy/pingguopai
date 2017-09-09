package com.xfl.pingguopai.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.xfl.pingguopai.dao.OrderMapper;
import com.xfl.pingguopai.dao.OrderTaskMapper;
import com.xfl.pingguopai.helper.OrderSO;
import com.xfl.pingguopai.helper.PageList;
import com.xfl.pingguopai.helper.enums.OrderStatus;
import com.xfl.pingguopai.model.Order;
import com.xfl.pingguopai.model.OrderTask;
import com.xfl.pingguopai.model.User;
import com.xfl.pingguopai.service.OrderService;
import com.xfl.pingguopai.common.AbstractServiceImpl;
import com.xfl.pingguopai.service.OrderTaskService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper tOrderMapper;

    @Autowired
    private OrderTaskService orderTaskService;


    @Override
    public int assign(Long orderId, Long userId) {
        logger.info("[OrderServiceImpl->assign] start orderId is {} ...", orderId);

        OrderTask orderTask = new OrderTask();
        orderTask.setOrderId(orderId);
        orderTask.setUserId(userId);
        int rtn = orderTaskService.save(orderTask);

        logger.info("[OrderServiceImpl->assign] end orderId is {} ...", orderId);
        return rtn;
    }

    @Override
    public PageList<Order> getOrderList(OrderSO so) {
        logger.info("[OrderServiceImpl->getOrderList] start ...");
        logger.info("[getOrderList->params] {}", JSON.toJSON(so));
        Condition condition = new Condition(Order.class);
        condition.createCriteria().andBetween("createTime", so.getBeginTime(), so.getEndTime());
        PageList<Order> pageList = selectPage(condition, so);
        List<Order> orderList = joinUser(pageList.getDatas());
        pageList.setDatas(orderList);
        logger.info("[OrderServiceImpl->getOrderList] end ...");
        return pageList;
    }

    public List<Order> joinUser(List<Order> orderList) {
        for (Order order : orderList) {
            User executor = orderTaskService.findExecutorByOrderId(order.getId());
            order.setUser(executor);
        }
        return orderList;
    }

    @Override
    public int saveAndAssign(Order order, Long userId) {
        logger.info("[OrderServiceImpl->saveAndAssign] start ...");
        Assert.notNull(userId, "用户不允许空");
        order.setOrderStatus(OrderStatus.UNEXECUTED.getCode());
        save(order);
        OrderTask orderTask = new OrderTask();
        orderTask.setOrderId(order.getId());
        orderTask.setUserId(userId);
        orderTask.setTaskStatus(OrderStatus.UNEXECUTED.getCode());
        int rtn = orderTaskService.save(orderTask);

        logger.info("[OrderServiceImpl->saveAndAssign] end ...");

        return rtn;
    }

    @Override
    public int excute(Long orderId, Integer orderStatus) {
        Assert.notNull(orderId, "必须传入用户id");
        findById(orderId);
        return 0;
    }
}
