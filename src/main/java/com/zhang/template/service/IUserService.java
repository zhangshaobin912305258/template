package com.zhang.template.service;

import com.zhang.template.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2020-03-30
 */
public interface IUserService extends IService<User> {

    User getByName(String userName);
}
