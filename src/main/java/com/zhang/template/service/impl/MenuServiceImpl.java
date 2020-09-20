package com.zhang.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.entity.Menu;
import com.zhang.template.entity.User;
import com.zhang.template.mapper.MenuMapper;
import com.zhang.template.service.MenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

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
}
