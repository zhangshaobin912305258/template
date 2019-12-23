package com.zhang.template.service;

import com.zhang.template.entity.SysMenu;
import com.zhang.template.entity.SysUser;
import com.zhang.template.security.GrantedAuthorityImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired private UserServiceImpl userService;
  @Autowired private MenuServiceImpl menuService;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    // 查数据库
    SysUser userDb = userService.selectByName(userName);
    if (userDb == null) {
      throw new UsernameNotFoundException("该用户不存在");
    }
    List<SysMenu> sysMenus = menuService.selectByUsername(userName);
    List<GrantedAuthorityImpl> grantedAuthorities =
        sysMenus.stream()
            .map(SysMenu::getPerms)
            .distinct()
            .filter(menu -> StringUtils.isNotEmpty(menu))
            .map(GrantedAuthorityImpl::new)
            .collect(Collectors.toList());
    userDb.setAuthorities(grantedAuthorities);
    return userDb;
  }
}
