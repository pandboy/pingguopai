package com.xfl.pingguopai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xfl.pingguopai.common.BaseModel;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
public class User extends BaseModel{

    /**
     * 用户名用手机号
     */
    @Column(name = "user_name")
    private String userName;

    @JsonIgnore
    private String password;

    /**
     * 员工名字
     */
    @Column(name = "emp_name")
    private String empName;

    @Column(name = "user_type")
    private Boolean userType;

    /**
     * 获取用户名用手机号
     *
     * @return user_name - 用户名用手机号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名用手机号
     *
     * @param userName 用户名用手机号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取员工名字
     *
     * @return emp_name - 员工名字
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置员工名字
     *
     * @param empName 员工名字
     */
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * @return user_type
     */
    public Boolean getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(Boolean userType) {
        this.userType = userType;
    }
}