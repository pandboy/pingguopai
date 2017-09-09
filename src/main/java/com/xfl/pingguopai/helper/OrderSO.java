package com.xfl.pingguopai.helper;

import com.xfl.pingguopai.common.LongDateTimeFormat;

import java.util.Date;

/**
 * Created by timely on 2017/9/2.
 */
public class OrderSO extends PageSO {
    @LongDateTimeFormat
    private Date beginTime;
    @LongDateTimeFormat
    private Date endTime;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
