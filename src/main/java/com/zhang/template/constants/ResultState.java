package com.zhang.template.constants;


public enum  ResultState {
    SUCCESS(200, "success"),
    INVALID_CAPTCHA(400,"验证码错误或已失效"),
    INCORRECT_USER(400,"用户名或密码错误"),
    INVALID_USER(400,"无效用户"),
    AUTHORIZATION_FAILED(403001, "认证失败,请检查权限"),
    LOCK_USER(400,"用户已被锁定"),
    IMAGE_ERROR(500001,"获取图片验证码失败");

    private int code;
    private String message;
    private String data;

    ResultState(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    ResultState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
