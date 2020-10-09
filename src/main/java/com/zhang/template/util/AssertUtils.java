package com.zhang.template.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zhang.template.constants.ResultState;
import com.zhang.template.exception.BusinessException;

import java.util.List;

public class AssertUtils {

    public static void isNotEmpty(CharSequence charSequence, ResultState resultState) {
        if (StrUtil.isEmpty(charSequence)) {
            throw new BusinessException(resultState);
        }
    }

    public static void isNotEmpty(CharSequence charSequence, ResultState resultState, String message) {
        resultState.setMessage(message);
        isNotEmpty(charSequence, resultState);
    }

    public static void isNotNull(Object object, ResultState resultState) {
        if (object == null) {
            throw new BusinessException(resultState);
        }
    }

    public static void isNotNull(Object object, ResultState resultState, String message) {
        resultState.setMessage(message);
        isNotNull(object, resultState);
    }

    public static void isNull(Object object, ResultState resultState) {
        if (object != null) {
            throw new BusinessException(resultState);
        }
    }

    public static void isNull(Object object, ResultState resultState, String message) {
        resultState.setMessage(message);
        isNull(object, resultState);
    }

    public static <T> void isNotEmpty(List<T> list, ResultState resultState) {
        if (CollUtil.isEmpty(list)) {
            throw new BusinessException(resultState);
        }
    }

    public static void isNotEqualZero(Integer num, ResultState resultState) {
        if (num == null || num == 0) {
            throw new BusinessException(resultState);
        }
    }
}
