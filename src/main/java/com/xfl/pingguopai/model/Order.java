package com.xfl.pingguopai.model;

import com.xfl.pingguopai.common.BaseModel;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order")
public class Order extends BaseModel {

    /**
     * 订单内容
     */
    @Column(name = "oder_content")
    private String oderContent;

    private String address;

    /**
     * 客户名字
     */
    @Column(name = "cus_name")
    private String cusName;

    /**
     * 客户手机号
     */
    @Column(name = "cus_phone")
    private String cusPhone;

    /**
     * 距离
     */
    private Double distance;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 分配对应的人
     */
    @Transient
    private User user;

    /**
     * 获取订单内容
     *
     * @return oder_content - 订单内容
     */
    public String getOderContent() {
        return oderContent;
    }

    /**
     * 设置订单内容
     *
     * @param oderContent 订单内容
     */
    public void setOderContent(String oderContent) {
        this.oderContent = oderContent;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取客户名字
     *
     * @return cus_name - 客户名字
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * 设置客户名字
     *
     * @param cusName 客户名字
     */
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * 获取客户手机号
     *
     * @return cus_phone - 客户手机号
     */
    public String getCusPhone() {
        return cusPhone;
    }

    /**
     * 设置客户手机号
     *
     * @param cusPhone 客户手机号
     */
    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    /**
     * 获取距离
     *
     * @return distance - 距离
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * 设置距离
     *
     * @param distance 距离
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * 获取订单状态
     *
     * @return order_status - 订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态
     *
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}