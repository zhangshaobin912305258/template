package com.zhang.template.service.impl;

import com.zhang.template.entity.Role;
import com.zhang.template.mapper.RoleMapper;
import com.zhang.template.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-09-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
