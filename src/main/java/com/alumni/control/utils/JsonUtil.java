package com.alumni.control.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * description : json工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 15:24
 */
public class JsonUtil {
    public JsonUtil() {
    }

    public static String toJSONString(Object obj) {
        String value;
        if (obj instanceof String) {
            value = obj + "";
        } else {
            value = JSONObject.toJSONString(obj);
        }

        return value;
    }

    public static <T> T toObject(String key, Class<T> clazz) {
        return JSONObject.parseObject(key, clazz);
    }

    public static <T> List<T> toList(String key, Class<T> clazz) {
        return JSONObject.parseArray(key, clazz);
    }
}
