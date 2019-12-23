package com.zhang.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色管理
 *
 * @author zhang
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole extends Model<SysRole> {

  private static final long serialVersionUID = 1L;

  /** 编号 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** 角色名称 */
  private String name;

  /** 备注 */
  private String remark;

  /** 创建人 */
  private String createBy;

  /** 创建时间 */
  private LocalDateTime createTime;

  /** 更新人 */
  private String lastUpdateBy;

  /** 更新时间 */
  private LocalDateTime lastUpdateTime;

  /** 是否删除 1：已删除 0：正常 */
  private Integer delFlag;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
