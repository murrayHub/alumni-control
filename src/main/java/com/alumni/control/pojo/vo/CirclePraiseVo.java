package com.alumni.control.pojo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * description : 动态点赞（目前不支持取消点赞）
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 15:36
 */
@Data
public class CirclePraiseVo {
    /**
     * 点赞编号
     */
    private String praiseId;

    /**
     * 动态编号
     */
    @NotBlank(message="动态编号不能为空")
    private String momentsId;

    /**
     * 点赞者
     */
    @NotBlank(message="点赞者不能为空")
    private String userId;

    /**
     * 点赞类型 1-动态朋友圈 2-公告 3-活动
     */
    @NotBlank(message="点赞类型不能为空")
    @Range(min=1,max=3,message="不支持的点赞类型")
    private String praiseType;

    /**
     * 点赞状态  0-未点赞  1-已点赞
     */
    private String status;

    /**
     * 点赞时间
     */
    private String praiseTime;

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
