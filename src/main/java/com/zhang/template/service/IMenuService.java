package com.zhang.template.service;

import com.zhang.template.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2020-03-30
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> listByName(String userName);
}
