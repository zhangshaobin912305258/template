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
 * 系统登录日志
 *
 * @author zhang
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLoginLog extends Model<SysLoginLog> {

  private static final long serialVersionUID = 1L;

  /** 编号 */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /** 用户名 */
  private String userName;

  /** 登录状态（online:在线，登录初始状态，方便统计在线人数；login:退出登录后将online置为login；logout:退出登录） */
  private String status;

  /** IP地址 */
  private String ip;

  /** 创建人 */
  private String createBy;

  /** 创建时间 */
  private LocalDateTime createTime;

  /** 更新人 */
  private String lastUpdateBy;

  /** 更新时间 */
  private LocalDateTime lastUpdateTime;

  @Override
  protected Serializable pkVal() {
    return this.id;
  }
}
