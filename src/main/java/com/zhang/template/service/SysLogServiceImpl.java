package com.zhang.template.service;

import com.zhang.template.entity.SysLog;
import com.zhang.template.mapper.SysLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统操作日志 服务实现类
 *
 * @author zhang
 * @since 2019-12-18
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> {}
