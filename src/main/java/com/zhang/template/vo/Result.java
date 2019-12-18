package com.zhang.template.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    @Builder.Default
    private int code = 200;
    private String message;
    private Object data;

    public static Result ok(String message,Object data) {
        return Result.builder()
                .code(200)
                .message(message)
                .data(data)
                .build();
    }
    public static Result ok(Object data) {
        return ok(null,data);
    }
}
