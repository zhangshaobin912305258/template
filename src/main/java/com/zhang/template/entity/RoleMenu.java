package com.zhang.template.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author zhang
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer menuId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
