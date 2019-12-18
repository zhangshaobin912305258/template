package com.zhang.template.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.template.entity.SysUser;
import com.zhang.template.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.vo.PageRequest;
import com.zhang.template.vo.PageResult;
import com.zhang.template.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> {
    private final SysUserMapper sysUserMapper;

    public SysUserServiceImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

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
}
