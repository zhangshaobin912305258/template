package com.zhang.template.service.impl;

import com.zhang.template.entity.Menu;
import com.zhang.template.entity.User;
import com.zhang.template.mapper.UserMapper;
import com.zhang.template.security.GrantedAuthorityImpl;
import com.zhang.template.service.IMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserMapper userMapper;
  private final IMenuService menuService;

  public UserDetailsServiceImpl(UserMapper userMapper, IMenuService menuService) {
    this.userMapper = userMapper;
    this.menuService = menuService;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    // 查数据库
    User userDb = userMapper.getByName(userName);
    if (userDb == null) {
      throw new UsernameNotFoundException("该用户不存在");
    }
    List<Menu> sysMenus = Collections.emptyList();
            //menuService.listByName(userName);
    List<GrantedAuthorityImpl> grantedAuthorities =
        sysMenus.stream()
            .map(Menu::getPerms)
            .distinct()
            .filter(menu -> StringUtils.isNotEmpty(menu))
            .map(GrantedAuthorityImpl::new)
            .collect(Collectors.toList());
    userDb.setAuthorities(grantedAuthorities);
    return userDb;
  }
}
