package com.alumni.control.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description : 注册邀请码-枚举类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 13:45
 */
@Getter
@AllArgsConstructor
public enum InvitationEnum {
    /**
     * 一级主操作员
     */
    ONE_LEVEL_MASTER("UCAS01", "一级主操作员"),
    /**
     * 一级从操作员
     */
    ONE_LEVEL_FOLLOW("UCAS02", "一级从操作员"),
    /**
     * 二级主操作员
     */
    TWO_LEVEL_MASTER("UCAS03", "二级主操作员"),
    /**
     * 二级从操作员
     */
    TWO_LEVEL_FOLLOW("UCAS04", "二级从操作员"),
    ;

    /**
     * code
     */
    private String code;

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
    public static String getDescByCode(String code) {
        for (InvitationEnum enums : values()) {
            if (code.equals(enums.getCode())) {
                return enums.getDesc();
            }
        }
        return null;
    }
}
