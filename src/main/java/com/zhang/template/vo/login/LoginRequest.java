package com.zhang.template.vo.login;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {
    private String username;
    private String password;
    private String code;
    private String uuid;
}
