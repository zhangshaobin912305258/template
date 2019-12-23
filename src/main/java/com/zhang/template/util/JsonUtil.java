package com.zhang.template.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

public class JsonUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    /**
     * bean转换为Json字符串
     * @param obj
     * @return
     */
    public static <JsonGenerator, SerializerProvider> String bean2Json(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Json字符串转换为实体类bean
     * @param jsonStr
     * @param objClass
     * @return
     */
    public static  <T> T json2Bean(String jsonStr, Class<T> objClass) {
        try {
            return objectMapper.readValue(jsonStr, objClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public  <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = objectMapper.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
