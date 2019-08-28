package com.swjtu.order.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author 李天峒
 * @date 2019/3/12 22:47
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 对象转换为json字符串
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转换位对象
     * @param string  json字符串
     * @param classType  对象类
     * @return
     */
    public static Object fromJson(String string, Class classType){
        try {
            return objectMapper.readValue(string,classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json字符串转换位对象list
     * @param string  json字符串
     * @param typeReference  对象类
     * @return
     */
    public static Object fromJson(String string, TypeReference typeReference){
        try {
            return objectMapper.readValue(string,typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
