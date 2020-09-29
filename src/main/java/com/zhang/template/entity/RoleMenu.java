package com.zhang.template.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author zhang
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer menuId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
