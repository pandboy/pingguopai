package com.xfl.pingguopai.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_login_session")
public class LoginSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creator;

    @Column(name = "create_time")
    private Date createTime;

    private String updater;

    @Column(name = "update_time")
    private Date updateTime;

    private Long version;

    private String remark;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户token
     */
    @Column(name = "user_token")
    private String userToken;

    /**
     * 有效期
     */
    @Column(name = "expire_date")
    private Date expireDate;

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
    public Long getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Long version) {
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
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户token
     *
     * @return user_token - 用户token
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * 设置用户token
     *
     * @param userToken 用户token
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    /**
     * 获取有效期
     *
     * @return expire_date - 有效期
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * 设置有效期
     *
     * @param expireDate 有效期
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}