package com.zhang.template.mapper;

import com.zhang.template.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2020-03-30
 */
public interface UserMapper extends BaseMapper<User> {

    User getByName(String userName);
}
