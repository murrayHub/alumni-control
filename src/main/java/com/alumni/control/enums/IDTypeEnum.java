package com.alumni.control.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description：IDTypeEnum
 *
 * @author : Murray on 2018/12/1
 * @version : 1.0.0
 */
@Getter
@AllArgsConstructor
public enum IDTypeEnum {

    /**
     * 用户号
     */
    USER(1, "用户号"),

    /**
     * 客户号
     */
    CUSTOMER(2, "客户号"),

    /**
     * 操作员编号
     */
    OPERATOR(3, "操作员编号"),;

    /**
     * code
     */
    private int code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 根据code获取描述
     *
     * @param type type
     * @return UserTypeEnum
     */
    public static String getContentMsg(int type) {
        for (IDTypeEnum item : IDTypeEnum.values()) {
            if (item.getCode() == type) {
                return item.getDesc();
            }
        }
        return null;
    }
}
