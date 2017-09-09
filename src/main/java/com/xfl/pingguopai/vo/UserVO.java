package com.xfl.pingguopai.vo;

import com.xfl.pingguopai.common.BaseVO;
import com.xfl.pingguopai.model.Order;

import java.util.List;

/**
 * Created by timely on 2017/9/4.
 */
public class UserVO extends BaseVO {
    /**
     * 用户名用手机号
     */
    private String username;

    /**
     * 员工名字
     */
    private String empName;

    private Double totalDistance;


    /**
     * 某段时间内所拥有的订单
     */
    private List<Order> orders;


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }
}
