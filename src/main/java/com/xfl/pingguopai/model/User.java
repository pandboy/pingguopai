package com.xfl.pingguopai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xfl.pingguopai.common.BaseModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "t_user")
public class User  extends BaseModel implements UserDetails{

    /**
     * 用户名用手机号
     */
    private String username;

    @JsonIgnore
    private String password;

    /**
     * 员工名字
     */
    @Column(name = "emp_name")
    private String empName;

    private Boolean enabled;

    @Transient
    private Date lastPasswordResetDate;

    @Transient
    private List<Authority> authorities;

    /**
     * 获取用户名用手机号
     *
     * @return user_name - 用户名用手机号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名用手机号
     *
     * @param username 用户名用手机号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}