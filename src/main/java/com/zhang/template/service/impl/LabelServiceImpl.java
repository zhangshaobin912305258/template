package com.zhang.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.template.constants.ApiConstants;
import com.zhang.template.constants.ResultState;
import com.zhang.template.entity.Label;
import com.zhang.template.mapper.LabelMapper;
import com.zhang.template.service.LabelService;
import com.zhang.template.util.AssertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhang
 * @since 2020-09-29
 */
@Service
@Transactional
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Override
    public void add(Label label) {
        AssertUtils.isNotNull(label, ResultState.PARAM_ERROR);
        String name = label.getName();
        AssertUtils.isNotEmpty(name, ResultState.PARAM_ERROR, ApiConstants.Message.NAME_EMPTY);
        Label labelDb = getByName(name);
        AssertUtils.isNotNull(labelDb, ResultState.PARAM_ERROR, ApiConstants.Message.LABEL_REPEAT);
        baseMapper.insert(label);
    }

    @Override
    public Label getByName(String name) {
        LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Label::getName, name);
        return getOne(queryWrapper);
    }
}
