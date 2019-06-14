package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

/**
 * 评论表
 */

@Data
@Table(name = "t_comment")
public class CommentDo {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 评论编号
     */
    @Column(name = "comment_id")
    private Long commentId;

    /**
     * 主题编号
     */
    @Column(name = "topic_id")
    private Long topicId;

    /**
     * 评论人
     */
    @Column(name = "from_uid")
    private Long fromUid;

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

    /**
     * 主题类型 1-动态
     */
    @Column(name = "topic_type")
    private Integer topicType;

    /**
     * 回复目标用户
     */
    @Column(name = "to_uid")
    private Long toUid;

    /**
     * 评论人姓名
     */
    @Column(name = "from_uid_name")
    private String fromUidName;

    /**
     * 回复目标用户姓名
     */
    @Column(name = "to_uid_name")
    private String toUidName;

}