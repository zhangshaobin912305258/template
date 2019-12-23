package com.zhang.template.mapper;

import com.zhang.template.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单管理 Mapper 接口
 *
 * @author zhang
 * @since 2019-12-18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

  List<SysMenu> selectByUsername(@Param(value = "username") String username);
}
