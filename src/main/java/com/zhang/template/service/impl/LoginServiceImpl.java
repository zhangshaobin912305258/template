package com.zhang.template.service.impl;

import com.zhang.template.constants.CaptchaConstants;
import com.zhang.template.constants.ResultState;
import com.zhang.template.exception.BusinessException;
import com.zhang.template.service.LoginService;
import com.zhang.template.util.AsyncFactory;
import com.zhang.template.util.AsyncManager;
import com.zhang.template.util.RedisCache;
import com.zhang.template.vo.login.LoginRequest;
import com.zhang.template.vo.user.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {


    private final TokenServiceImpl tokenService;

    private final AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param request     登录请求
     * @return 结果
     */
    public String login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        String code = request.getCode();
        String uuid = request.getUuid();
        String verifyKey = CaptchaConstants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(/*username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"))*/));
            throw new BusinessException(ResultState.INVALID_CAPTCHA);
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(/*username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")*/));
            throw new BusinessException(ResultState.INVALID_CAPTCHA);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(/*username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")*/));
                throw new BusinessException(ResultState.INCORRECT_USER);
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(/*username, Constants.LOGIN_FAIL, e.getMessage()*/));
                throw new BusinessException(ResultState.INCORRECT_USER);
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(/*username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")*/));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
