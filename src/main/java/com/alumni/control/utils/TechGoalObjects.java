package com.alumni.control.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * description : 判空-工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 17:27
 */
public class TechGoalObjects {
    private TechGoalObjects(){}
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(Map str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(Collection str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(Object[] str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty((CharSequence)str);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isEmpty(Object str) {
        return str == null;
    }

    public static boolean isEmpty(Map str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(Collection str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(Object[] str) {
        return str == null || str.length == 0;
    }

    public static boolean isEmpty(CharSequence str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isBlank(CharSequence str) {
        return StringUtils.isBlank(str);
    }
}
