package com.zhang.template.vo.route;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 前端路由信息
 */
@Data
public class RouteInfoVo implements Serializable {

    private String path;
    private String name;
    private String label;
    private String icon;
    private boolean jump;
    private boolean directory;
    private List<RouteInfoVo> children;
}
