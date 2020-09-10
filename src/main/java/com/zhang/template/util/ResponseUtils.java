package com.zhang.template.util;

import cn.hutool.json.JSONUtil;
import com.zhang.template.constants.ResultState;
import com.zhang.template.vo.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param resultState 结果枚举对象
     * @param msg   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, ResultState resultState, String msg) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Result result = Result.builder()
                    .code(resultState.getCode())
                    .message(msg)
                    .build();
            response.getWriter().print(JSONUtil.toJsonStr(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
