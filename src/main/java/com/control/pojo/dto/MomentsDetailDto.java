package com.techGoal.pojo.dto;

import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * description : 个人动态
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/7 15:06
 */
@Data
public class MomentsDetailDto {
    /**
     * 动态编号
     */
    private String momentsId;

    /**
     * 圈编号
     */
    private String circleNo;
    /**
     * 圈名称
     */
    private String circleName;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 组图（多个图片地址用逗号隔开）
     */
    private String images;

    /**
     * 状态：1-成功 2-草稿 3-失败 4-撤回
     */
    private String status;

    /**
     * 评论权限 1:允许评论,0:不允许评论
     */
    private String commentFlag;

    /**
     * 发布位置
     */
    private String location;

    /**
     * 发布人
     */
    private String publisherId;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 发布时间
     */
    private String publisherTime;

    /**
     * 更新时间
     */
    private String updateAt;
    /**
     * 留言统计
     */
    @Transient
    private String commentsCount;
    /**
     * 点赞统计
     */
    @Transient
    private String praiseCount;

    /**
     * 留言列表
     */
    private List<CommentDto> commentList;

}
