package com.zhang.template.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequest implements Serializable {
    //当前页
    private int page;
    //每页数量
    private int pageSize;
    //查询参数
    @Builder.Default
    private Map<String,Object> params = new HashMap<>();

}
