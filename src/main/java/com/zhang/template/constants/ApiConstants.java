package com.zhang.template.constants;

public class ApiConstants {

    public static final int ZERO = 0;

    public static final String EMPTY = "";

    public static class Message {
        public static final String TITLE_NOT_EMPTY = "标题不能为空";
        public static final String TITLE_REPEAT = "标题重复";
        public static final String NAME_EMPTY = "名字不能为空";
        public static final String LABEL_REPEAT = "标签重复";
        public static final String ARTICLE_NOT_EXIST = "文章不存在";
    }

    public static class Order {
        public static final String ASC = "asc";
        public static final String DESC = "desc";
    }

    public static class StatusConstant {
        public static final int NORMAL = 0;
        public static final int CLOSE = -1;
    }
}
