package com.zhang.template.controller;

import cn.hutool.core.map.MapUtil;
import com.zhang.template.constants.TokenConstants;
import com.zhang.template.service.LoginService;
import com.zhang.template.vo.Result;
import com.zhang.template.vo.login.LoginRequest;
import com.zhang.template.vo.route.RouteInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return Result.ok(MapUtil.builder()
                .put(TokenConstants.TOKEN, token)
                .put("routes", buildRouteInfos())
                .build());
    }

    @GetMapping("/getRoutes")
    public Result getRoutes() {
        return Result.ok(MapUtil.builder()
                .put("routes", buildRouteInfos())
                .build());
    }

    public List<RouteInfoVo> buildRouteInfos() {
        List<RouteInfoVo> result = new ArrayList<>();

        RouteInfoVo routeInfo1 = new RouteInfoVo();
        routeInfo1.setPath("user");
        routeInfo1.setName("user");
        routeInfo1.setLabel("用户管理");
        routeInfo1.setDirectory(true);
        routeInfo1.setIcon("");
        routeInfo1.setJump(false);

        List<RouteInfoVo> userChildren = new ArrayList<>();

        RouteInfoVo userRoute = new RouteInfoVo();
        userRoute.setChildren(Collections.emptyList());
        userRoute.setLabel("用户列表");
        userRoute.setName("userList");
        userRoute.setPath("user/List");
        userRoute.setDirectory(false);
        userRoute.setJump(true);

        userChildren.add(userRoute);
        routeInfo1.setChildren(userChildren);
        result.add(routeInfo1);
        return result;
    }

}
