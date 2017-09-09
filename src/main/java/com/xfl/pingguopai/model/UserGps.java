package com.xfl.pingguopai.model;

import com.xfl.pingguopai.common.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_gps")
public class UserGps extends BaseModel{

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 经度
     */
    private String latitude;

    /**
     * 维度
     */
    private String longitude;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取经度
     *
     * @return latitude - 经度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置经度
     *
     * @param latitude 经度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取维度
     *
     * @return longitude - 维度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置维度
     *
     * @param longitude 维度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}