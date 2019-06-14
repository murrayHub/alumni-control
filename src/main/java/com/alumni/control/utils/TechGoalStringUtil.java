package com.alumni.control.utils;

/**
 * description : 字符串工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 18:00
 */
public class TechGoalStringUtil {
    private TechGoalStringUtil() {
    }

    public static String leftAppendZero(int length, String str) {
        return appendStr(length, Boolean.TRUE, str, "0");
    }

    public static String appendStr(int length, Boolean leftBoo, String str, String appendStr) {
        if (str == null) {
            str = "";
        }

        StringBuilder sb = new StringBuilder();
        if (!leftBoo) {
            sb.append(str);
        }

        while(sb.length() < length) {
            sb.append(appendStr);
        }

        if (leftBoo) {
            sb.append(str);
        }

        return sb.toString();
    }

    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();

        for(int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");

        for(int i = 1; i < hex.length; ++i) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char)data);
        }

        return string.toString();
    }
}
