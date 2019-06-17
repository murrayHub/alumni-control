package com.alumni.control.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 13:45
 */
@Getter
@AllArgsConstructor
public enum DegreeLevelEnum {
    /**
     * 学士
     */
    BACHELOR(1, "学士"),
    /**
     * 硕士
     */
    MASTER(2, "硕士"),
    /**
     * 博士
     */
    DOCTOR(3, "博士"),
    /**
     * 其他
     */
    OTHER(4, "其他"),
    ;

    /**
     * code
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 根据Code获取Desc
     *
     * @param code String
     * @return 结果集
     */
    public static String getDescByCode(Integer code) {
        for (DegreeLevelEnum enums : values()) {
            if (code.equals(enums.getCode())) {
                return enums.getDesc();
            }
        }
        return null;
    }
}
