package com.zhang.template.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.template.entity.SysUser;
import com.zhang.template.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.util.JwtTokenUtil;
import com.zhang.template.vo.*;
import com.zhang.template.vo.constEnum.ResultState;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户管理 服务实现类
 *
 * @author zhang
 * @since 2019-12-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> {
  @Autowired private SysUserMapper sysUserMapper;
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private JwtTokenUtil jwtTokenUtil;

  @Autowired private UserDetailsService userDetailsService;

  public Result findPage(PageRequest pageRequest) {
    int page = pageRequest.getPage();
    int pageSize = pageRequest.getPageSize();
    Map<String, Object> params = pageRequest.getParams();
    Page<SysUser> userPage =
        this.sysUserMapper.selectPage(new Page<>(page, pageSize), new QueryWrapper<>());
    List<SysUser> records = userPage.getRecords();
    List<SysUser> recordsTemp =
        records.stream()
            .map(
                sysUser -> {
                  SysUser sysUserTemp = new SysUser();
                  BeanUtils.copyProperties(sysUser, sysUserTemp, "password", "salt");
                  return sysUserTemp;
                })
            .collect(Collectors.toList());
    userPage.setRecords(recordsTemp);
    return Result.ok(PageResult.convert(userPage));
  }

  /**
   * 根据用户名查询用户
   *
   * @param username
   * @return
   */
  public SysUser selectByName(String username) {
    return sysUserMapper.selectOne(
        new LambdaQueryWrapper<SysUser>().eq(SysUser::getName, username));
  }

  public Result login(LoginVo loginInfo, HttpServletRequest request) {
    String username = loginInfo.getUsername();
    SysUser userDb = selectByName(username);
    if (userDb == null) {
      return Result.error(ResultState.INCORRECT_USER);
    }
    String password = loginInfo.getPassword();
    /*if (! PasswordUtils.matches(userDb.getSalt(),password,userDb.getPassword())) {
        return Result.error(ResultState.INCORRECT_USER);
    }*/
    if (userDb.getStatus() == 1) {
      return Result.error(ResultState.LOCK_USER);
    }
    // 进行系统登录认证
    // JwtAuthenticationToken token = SecurityUtils.login(request, username, password,
    // authenticationManager);
    UsernamePasswordAuthenticationToken upToken =
        new UsernamePasswordAuthenticationToken(username, password);
    Authentication authentication = authenticationManager.authenticate(upToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    String token = jwtTokenUtil.generateToken(userDetails);
    return Result.ok(token);
  }
}
