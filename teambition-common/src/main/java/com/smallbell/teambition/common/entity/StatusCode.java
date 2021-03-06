package com.smallbell.teambition.common.entity;

/**
 * 状态码
 */
public class StatusCode {
    //成功
    public static final int OK = 20000;
    //失败
    public static final int ERROR = 20001;
    //用户名或者密码错误
    public static final int LOGIN_ERROR = 20002;
    //权限不足
    public static final int ACCESS_ERROR = 20003;
    //远程调用失败
    public static final int REMOTE_ERROR = 20004;
    //重复操作
    public static final int REPEAT_ERROR = 20005;

}
