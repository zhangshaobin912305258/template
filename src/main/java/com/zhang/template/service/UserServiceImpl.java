package com.zhang.template.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.template.config.GrantedAuthorityImpl;
import com.zhang.template.config.JwtAuthenticationToken;
import com.zhang.template.entity.SysMenu;
import com.zhang.template.entity.SysUser;
import com.zhang.template.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.util.PasswordUtils;
import com.zhang.template.util.SecurityUtils;
import com.zhang.template.vo.*;
import com.zhang.template.vo.constEnum.ResultState;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2019-12-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService {
    @Autowired
    private  SysUserMapper sysUserMapper;
    @Autowired
    private MenuServiceImpl menuService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public Result findPage(PageRequest pageRequest) {
        int page = pageRequest.getPage();
        int pageSize = pageRequest.getPageSize();
        Map<String, Object> params = pageRequest.getParams();
        Page<SysUser> userPage = this.sysUserMapper.selectPage(new Page<>(page, pageSize), new QueryWrapper<>());
        List<SysUser> records = userPage.getRecords();
        List<SysUser> recordsTemp = records.stream().map(sysUser -> {
            SysUser sysUserTemp = new SysUser();
            BeanUtils.copyProperties(sysUser, sysUserTemp, "password", "salt");
            return sysUserTemp;
        }).collect(Collectors.toList());
        userPage.setRecords(recordsTemp);
        return Result.ok(PageResult.convert(userPage));
    }

    public Result login(LoginVo loginInfo, HttpServletRequest request) {
        String username = loginInfo.getUsername();
        SysUser userDb = selectByName(username);
        if (userDb != null) {
            //校验密码信息
            if (!PasswordUtils.matches(userDb.getSalt(),loginInfo.getPassword(),userDb.getPassword())) {
                return Result.error(ResultState.INCORRECT_USER);
            }
            if (userDb.getStatus() == 1) {
                return Result.error(ResultState.LOCK_USER);
            }
            //进行登录认证
            JwtAuthenticationToken token = SecurityUtils.login(request,username,userDb.getPassword(),authenticationManager);
            return Result.ok(token);
        }
        return Result.error(ResultState.INCORRECT_USER);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userDb = selectByName(username);
        if (userDb == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        //查询用户具有的权限
        List<SysMenu> sysMenus = menuService.selectByUsername(username);
        List<GrantedAuthorityImpl> grantedAuthorities = sysMenus.stream()
                .distinct()
                .filter(sysMenu -> StringUtils.isNotEmpty(sysMenu.getPerms()))
                .map(SysMenu::getPerms)
                .distinct()
                .map(GrantedAuthorityImpl::new)
                .collect(Collectors.toList());
        return JwtUserDetails.builder()
                .username(userDb.getName())
                .password(userDb.getPassword())
                .salt(userDb.getSalt())
                .authorities(grantedAuthorities)
                .build();
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public SysUser selectByName(String username) {
        return sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getName, username));
    }



}
