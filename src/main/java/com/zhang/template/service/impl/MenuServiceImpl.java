package com.zhang.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.constants.ResultState;
import com.zhang.template.entity.Menu;
import com.zhang.template.entity.User;
import com.zhang.template.mapper.MenuMapper;
import com.zhang.template.service.MenuService;
import com.zhang.template.service.UserService;
import com.zhang.template.util.AssertUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-09-10
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final UserService userService;


    /**
     * 获取用户具有的权限
     * @param user
     * @return
     */
    @Override
    public Set<String> getMenuPermission(User user) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if ("admin".equals(user.getUsername())) {
            perms.add("*:*:*");
        } else {
            perms.addAll(baseMapper.selectMenuPermsByUserId(user.getId()));
        }
        return perms;
    }

    /**
     * 获取用户具有的权限
     * @param username
     * @return
     */
    @Override
    public Set<String> listByUsername(String username) {
        User userDb = userService.getByName(username);
        AssertUtils.isNotNull(userDb, ResultState.INCORRECT_USER);
        return getMenuPermission(userDb);
    }
}
