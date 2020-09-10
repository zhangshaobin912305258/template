package com.zhang.template.controller;

import cn.hutool.core.map.MapUtil;
import com.zhang.template.constants.TokenConstants;
import com.zhang.template.service.LoginService;
import com.zhang.template.vo.Result;
import com.zhang.template.vo.login.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    /**
     * 登录方法
     *
     * @param request 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        // 生成令牌
        String token = loginService.login(request);
        return Result.ok(MapUtil.builder().put(TokenConstants.TOKEN, token).build());
    }

}
