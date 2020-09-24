package com.zhang.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.template.entity.Menu;
import com.zhang.template.entity.User;

import java.util.Set;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author zhang
 * @since 2020-09-10
 */
public interface MenuService extends IService<Menu> {

    Set<String> getMenuPermission(User user);

    Set<String> listByUsername(String username);
}
