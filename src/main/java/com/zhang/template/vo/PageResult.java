package com.zhang.template.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult {
    //当前页
    private long page;
    //每页数量
    private long pageSize;
    //记录总数
    private long totalSize;
    //页码总数
    private long totalPages;
    //分页数据
    private List<?> content;
    //是否有上一页
    private boolean hasPrevious;
    //是否有下一页
    private boolean hasNext;

    public static PageResult convert(Page page) {
        if (page == null) {
            return null;
        }
        return PageResult.builder()
                .content(page.getRecords())
                .page(page.getCurrent())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .pageSize(page.getSize())
                .totalPages(page.getTotal()%page.getSize() == 0 ? page.getTotal()/page.getSize():(page.getTotal()/page.getSize()+1))
                .totalSize(page.getTotal())
                .build();
    }
}
