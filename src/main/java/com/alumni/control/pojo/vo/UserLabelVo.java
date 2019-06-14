package com.alumni.control.pojo.vo;

import lombok.Data;

/**
 * description : 用户标签集合
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 16:30
 */
@Data
public class UserLabelVo {
    /**
     * 标签编号
     */
    private String labelId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 状态  0-无效 1-有效
     */
    private String status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private String createAt;

    /**
     * 更新时间
     */
    private String updateAt;
}
