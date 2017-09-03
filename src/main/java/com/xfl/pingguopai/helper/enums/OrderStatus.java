package com.xfl.pingguopai.helper.enums;

/**
 * Created by timely on 2017/9/2.
 */
public enum  OrderStatus {
    UNEXECUTED(1),
    EXECUTING(3),
    EXECUTED(5);

    private int code;
    private OrderStatus(int code) {
       this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
