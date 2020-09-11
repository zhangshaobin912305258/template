package com.zhang.template.mapper;

import com.zhang.template.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2020-09-10
 */
public interface MenuMapper extends BaseMapper<Menu> {

    Set<String> selectMenuPermsByUserId(@Param("id") Integer id);
}
