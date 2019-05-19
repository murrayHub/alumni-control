package com.techGoal.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_circle_comment")
public class CircleCommentDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 评论编号
     */
    @Column(name = "comment_id")
    private Long commentId;

    /**
     * 动态编号
     */
    @Column(name = "moments_id")
    private Long momentsId;

    /**
     * 评论人
     */
    @Column(name = "reviewer_id")
    private Long reviewerId;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态 0-失效 1-有效(由动态发布者控制)
     */
    @Column(name = "vaild_status")
    private Integer vaildStatus;

    /**
     * 状态 0-失效 1-有效(由评论者本人控制)
     */
    private Integer enabled;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

}