package com.zhang.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Menu对象", description="")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    private Integer parentId;

    @ApiModelProperty(value = "菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， * iframe:/druid/login.html, iframe:前缀会替换成服务器地址)")
    private String url;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)")
    private String perms;

    @ApiModelProperty(value = " 类型 0：目录 1：菜单 2：按钮")
    private Long type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String lastUpdateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime lastUpdateTime;

    @ApiModelProperty(value = "是否删除 1：已删除 0：正常")
    private Long delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
