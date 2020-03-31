package com.zhang.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

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
@ApiModel(value="User对象", description="")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> implements UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "加密盐")
    private String salt;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "状态 0：正常 1：禁用")
    private Long status;

    @ApiModelProperty(value = "机构ID")
    private Integer deptId;

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

    /** 权限列表 */
    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /** 用户账号是否过期 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** 用户账号是否被锁定 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** 用户密码是否过期 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 用户是否可用 */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
