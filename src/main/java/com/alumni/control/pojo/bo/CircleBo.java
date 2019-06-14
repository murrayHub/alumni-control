package com.alumni.control.pojo.bo;

import lombok.Data;

import java.util.Date;

/**
 * description : 交流圈信息
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 22:15
 */
@Data
public class CircleBo {

    /**
     * 圈编号
     */
    private String circleNo;

    /**
     * 圈主
     */
    private String circleHostNo;

    /**
     * 圈名称
     */
    private String circleName;

    /**
     * 圈主题
     */
    private String circleTheme;

    /**
     * 圈标签
     */
    private String circleLabel;

    /**
     * 圈头像
     */
    private String image;

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
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    /**
     * 成员人数
     */
    private String memberCounts;

}