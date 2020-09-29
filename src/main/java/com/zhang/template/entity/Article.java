package com.zhang.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 查看数量
     */
    private Integer watchCount;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 缩略图
     */
    private String picture;

    /**
     * 导航主键
     */
    private Integer navId;

    /**
     * 是否推荐 0:否 1:是
     */
    private Integer recommend;

    /**
     * 是否是草稿箱 0:否 1:是
     */
    private Integer draft;

    /**
     * 状态 0:正常 -1:停用
     */
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
