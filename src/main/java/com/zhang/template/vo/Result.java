package com.zhang.template.vo;

import com.zhang.template.constants.ResultState;
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

    public static Result ok(String message, Object data) {
        return Result.builder()
                .code(200)
                .message(message)
                .data(data)
                .build();
    }

    public static Result ok() {
        return ok("success", null);
    }

    public static Result ok(Object data) {
        return ok("success", data);
    }

    public static Result error(ResultState resultState) {
        return Result.builder()
                .code(resultState.getCode())
                .message(resultState.getMessage())
                .data(resultState.getData())
                .build();
    }

    public static Result ok(int code, String message, Object data) {
        System.out.println(111);
        System.out.println(222);
        return Result.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
