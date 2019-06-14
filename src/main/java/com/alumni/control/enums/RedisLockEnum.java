package com.alumni.control.enums;

/**
 * description : Redis缓存枚举类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2018/11/20 20:48
 */
public enum RedisLockEnum {
    INIT("INIT", "初始化"),
    TRUE("TRUE", "是"),
    FALSE("FALSE", "否");

    private String code;
    private String desc;

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    private RedisLockEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
