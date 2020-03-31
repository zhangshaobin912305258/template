package com.zhang.template.mapper;

import com.zhang.template.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2020-03-30
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listByName(String userName);
}
