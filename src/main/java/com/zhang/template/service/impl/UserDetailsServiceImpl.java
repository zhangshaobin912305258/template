package com.zhang.template.service.impl;

import com.zhang.template.constants.ResultState;
import com.zhang.template.entity.User;
import com.zhang.template.exception.BusinessException;
import com.zhang.template.service.MenuService;
import com.zhang.template.service.UserService;
import com.zhang.template.vo.user.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByName(username);
        if (user == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new BusinessException(ResultState.INCORRECT_USER);
        } /*else if (1 == user.getDelFlag()) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BusinessException(ResultState.INCORRECT_USER);
        } */else if (2 == user.getStatus()) {
            log.info("登录用户：{} 已被停用.", username);
            throw new BusinessException(ResultState.LOCK_USER);
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(User user) {
        return new LoginUser(user, menuService.getMenuPermission(user));
    }
}
