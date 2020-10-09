package com.zhang.template.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.template.constants.ApiConstants;
import com.zhang.template.constants.ResultState;
import com.zhang.template.entity.Label;
import com.zhang.template.exception.BusinessException;
import com.zhang.template.mapper.LabelMapper;
import com.zhang.template.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-09-29
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Override
    public void add(Label label) {
        if (label == null) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR);
        }
        String name = label.getName();
        if (StrUtil.isBlank(name)) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR, ApiConstants.Message.NAME_EMPTY);
        }
        Label labelDb = getByName(name);
        if(labelDb != null) {
            throw new BusinessException(ResultState.PARAM_CUSTOM_ERROR, ApiConstants.Message.LABEL_REPEAT);
        }
        baseMapper.insert(label);
    }

    @Override
    public Label getByName(String name) {
        LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Label::getName, name);
        return getOne(queryWrapper);
    }
}
