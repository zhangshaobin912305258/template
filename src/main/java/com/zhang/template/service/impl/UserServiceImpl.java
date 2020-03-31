package com.zhang.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.entity.User;
import com.zhang.template.mapper.UserMapper;
import com.zhang.template.service.IUserService;
import com.zhang.template.util.JwtTokenUtil;
import com.zhang.template.util.PasswordEncoder;
import com.zhang.template.vo.LoginVo;
import com.zhang.template.vo.Result;
import com.zhang.template.vo.constEnum.ResultState;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-03-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenUtil jwtTokenUtil,
                           @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public User getByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return getOne(queryWrapper);
    }

    public Result login(LoginVo loginInfo, HttpServletRequest request) {
        String username = loginInfo.getUsername();
        User userDb = getByName(username);
        if (userDb == null) {
            return Result.error(ResultState.INCORRECT_USER);
        }
        if (userDb.getStatus() == 1) {
            return Result.error(ResultState.LOCK_USER);
        }
        String password = loginInfo.getPassword();
        if (!PasswordEncoder.matches(password,userDb)) {
            return Result.error(ResultState.INCORRECT_USER);
        }
        // 进行系统登录认证
        // JwtAuthenticationToken token = SecurityUtils.login(request, username, password,
        // authenticationManager);
        UsernamePasswordAuthenticationToken upToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return Result.ok(token);
    }
}
