package com.zhang.template.vo.constEnum;


public enum  ResultState {
    INVALID_CAPTCHA(400,"验证码错误或已失效"),
    INCORRECT_USER(400,"用户名或密码错误"),
    LOCK_USER(400,"用户已被锁定");

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
