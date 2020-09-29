package com.zhang.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Navigation extends Model<Navigation> {

    private static final long serialVersionUID = 1L;

    /**
     * 导航主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 导航显示顺序
     */
    private Integer orderNum;

    /**
     * 状态 0:正常 -1:停用
     */
    private Integer status;

    /**
     * 父导航id
     */
    private Integer parentId;

    @TableField(exist = false)
    private List<Navigation> children;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
