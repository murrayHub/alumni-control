package com.alumni.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description : 朋友圈动态
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/30 10:44
 */
@Data
public class MomentsVo {
    /**
     * 动态编号
     */
    @ApiModelProperty(value = "动态编号")
    private String momentsId;

    /**
     * 圈编号
     */
    @ApiModelProperty(value = "圈编号")
    private String circleNo;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 组图（多个图片地址用逗号隔开）
     */
    @ApiModelProperty(value = "组图")
    private String images;

    /**
     * 状态：1-成功 2-草稿 3-失败 4-撤回
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 评论权限 1:允许评论,0:不允许评论
     */
    @ApiModelProperty(value = "评论权限")
    private String commentFlag;

    /**
     * 发布位置
     */
    @ApiModelProperty(value = "发布位置")
    private String location;

    /**
     * 发布人
     */
    @ApiModelProperty(value = "发布人")
    private String publisherId;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private String publisherTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private String updateAt;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userId;
}
