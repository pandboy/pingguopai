package com.xfl.pingguopai.common;

/**
 * 项目常量
 */
public final class MyConstant {
    /**
     * 基础包
     */
    public static final String BASE_PACKAGE = "com.xfl.pingguopai";

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web";//Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".common.Mapper";//Mapper插件基础接口的完全限定名
    public static final String SALT = "FIRSTAPPLE2";
}
