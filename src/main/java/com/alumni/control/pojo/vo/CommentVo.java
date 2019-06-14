package com.alumni.control.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description : 动态评论
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 15:04
 */
@Data
public class CommentVo {
    /**
     * 评论编号
     */
    private String commentId;

    /**
     * 主题编号
     */
    @NotBlank(message="主题编号不能为空")
    private String topicId;

    /**
     * 评论人
     */
    @NotBlank(message="评论人不能为空")
    private String fromUid;

    /**
     * 评论内容
     */
    @NotBlank(message="评论内容不能为空")
    private String content;

    /**
     * 状态 0-失效 1-有效(由动态发布者控制)
     */
    private String vaildStatus;

    /**
     * 状态 0-失效 1-有效(由评论者本人控制)
     */
    private String enabled;

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

    /**
     * 主题类型 1-动态
     */
    @NotBlank(message="主题类型不能为空")
    private String topicType;

    /**
     * 回复目标用户
     */
    private String toUid;

    /**
     * 评论人姓名
     */
    private String fromUidName;

    /**
     * 回复目标用户姓名
     */
    private String toUidName;
}
