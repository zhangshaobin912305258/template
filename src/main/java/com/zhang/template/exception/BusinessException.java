package com.zhang.template.exception;

import com.zhang.template.constants.ResultState;


public class BusinessException extends RuntimeException {

    private int code;
    private String message;

    public BusinessException(ResultState resultState) {
        this.code = resultState.getCode();
        this.message = resultState.getMessage();
    }

    public BusinessException(ResultState resultState, String message) {
        this.code = resultState.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
