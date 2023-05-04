package com.fetion.base.constant;

/**
 * 关于session的所有常量统一存放类
 *
 * @author 卞宇轩
 */
public class SessionConstant {

    public static final String SESSION_USER_LOGIN_KEY = "user";
    public static final String SESSION_ACCOUNT_LOGIN_KEY = "base_account";
    public static final String SESSION_USER_AUTH_KEY = "base_auth";

    /**
     * 管理端cookie名称
     */
    public static final String COOKIE_NAME_ADMIN = "ADMINTOKEN";
    /**
     * 前端cookie名称
     */
    public static final String COOKIE_NAME_APP = "APPTOKEN";
    /**
     * 登录缓存时间，先设置2小时
     */
    public static final int SESSION_TIME_USER_LOGIN = 3600 * 2;
}
