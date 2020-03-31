package com.zhang.template.service.impl;

import com.zhang.template.entity.Log;
import com.zhang.template.mapper.LogMapper;
import com.zhang.template.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-03-30
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
