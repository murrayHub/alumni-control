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
public enum IdentifyStatusEnum {
    /**
     * 未认证
     */
    NO_IDENTIFIED(0, "未认证"),
    /**
     * 二级认证待审核
     */
    WAIT_LEVEL_TWO_AUDIT(1, "二级认证待审核"),
    /**
     * 二级认证初审通过
     */
    LEVEL_TWO_FIRST_AUDIT_SUCCESS(2, "二级认证初审通过"),
    /**
     * 二级认证初审失败
     */
    LEVEL_TWO_FIRST_AUDIT_FAILED(3, "二级认证初审失败"),
    /**
     * 二级认证复审通过
     */
    LEVEL_TWO_SECOND_AUDIT_SUCCESS(4, "二级认证复审通过"),
    /**
     * 二级认证复审失败
     */
    LEVEL_TWO_SECOND_AUDIT_FAILED(5, "二级认证复审失败"),
    /**
     * 一级认证待审核
     */
    WAIT_LEVEL_ONE_AUDIT(6, "一级认证待审核"),
    /**
     * 一级认证初审通过
     */
    LEVEL_ONE_FIRST_AUDIT_SUCCESS(7, "一级认证初审通过"),
    /**
     * 一级认证初审失败
     */
    LEVEL_ONE_FIRST_AUDIT_FAILED(8, "一级认证初审失败"),
    /**
     * 一级认证复审通过
     */
    LEVEL_ONE_SECOND_AUDIT_SUCCESS(9, "一级认证复审通过"),
    /**
     * 一级认证复审失败
     */
    LEVEL_ONE_SECOND_AUDIT_FAILED(10, "一级认证复审失败"),
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
        for (IdentifyStatusEnum enums : values()) {
            if (code.equals(enums.getCode())) {
                return enums.getDesc();
            }
        }
        return null;
    }
}
