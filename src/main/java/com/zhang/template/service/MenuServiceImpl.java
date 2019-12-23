package com.zhang.template.service;

import com.zhang.template.entity.SysMenu;
import com.zhang.template.mapper.SysMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单管理 服务实现类
 *
 * @author zhang
 * @since 2019-12-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> {
  @Autowired private SysMenuMapper sysMenuMapper;

  public List<SysMenu> selectByUsername(String username) {
    if ("admin".equalsIgnoreCase(username)) {
      return sysMenuMapper.selectList(null);
    }
    return sysMenuMapper.selectByUsername(username);
  }
}
