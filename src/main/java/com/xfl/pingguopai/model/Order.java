package com.xfl.pingguopai.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String updater;

    @Column(name = "update_time")
    private Date updateTime;

    private Integer version;

    private String remark;

    /**
     * 订单名字
     */
    @Column(name = "oder_name")
    private String oderName;

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
    private Boolean orderStatus;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return updater
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * @param updater
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取订单名字
     *
     * @return oder_name - 订单名字
     */
    public String getOderName() {
        return oderName;
    }

    /**
     * 设置订单名字
     *
     * @param oderName 订单名字
     */
    public void setOderName(String oderName) {
        this.oderName = oderName;
    }

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
    public Boolean getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态
     *
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}