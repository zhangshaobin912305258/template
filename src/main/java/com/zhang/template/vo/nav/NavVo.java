package com.zhang.template.vo.nav;

import com.zhang.template.entity.Navigation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NavVo implements Serializable {

    /**
     * 导航主键
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

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

    private List<Navigation> children;
}
