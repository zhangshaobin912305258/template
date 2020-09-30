package com.zhang.template.service;

import com.zhang.template.entity.Label;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2020-09-29
 */
public interface LabelService extends IService<Label> {

    void add(Label label);

    Label getByName(String name);
}
