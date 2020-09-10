package com.zhang.template.service;

import com.zhang.template.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhang
 * @since 2020-09-10
 */
public interface UserService extends IService<User> {

    User getByName(String username);
}
