package com.zhang.template.vo.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageRequest implements Serializable {
    private String key;
    private int page;
    private int size;
    private List<Order> orders;

    @Data
    public static class Order {
        private String column;
        private String order;
    }
}
