package com.zhang.template.security;

import com.zhang.template.constants.ResultState;
import com.zhang.template.service.impl.TokenServiceImpl;
import com.zhang.template.util.AsyncFactory;
import com.zhang.template.util.AsyncManager;
import com.zhang.template.util.ServletUtils;
import com.zhang.template.vo.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出成功处理类
 */
@Configuration
@RequiredArgsConstructor
public class LogoutSuccessHandleImpl implements LogoutSuccessHandler {

    private final TokenServiceImpl tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser != null) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(/*userName, Constants.LOGOUT, "退出成功"*/));
        }
        ServletUtils.renderString(response, ResultState.SUCCESS, "退出成功");
    }
}
