package com.xfl.pingguopai.helper.enums;

/**
 * Created by timely on 2017/9/2.
 */
public enum UserType {
    ADMIN(1), USER(3);
    private int value;
    private UserType (int value) {
        this.value = value;
    }
    public int value() { return this.value; }
}
