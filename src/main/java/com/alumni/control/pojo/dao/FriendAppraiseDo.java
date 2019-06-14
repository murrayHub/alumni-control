package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_friend_appraise")
public class FriendAppraiseDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 评价编号
     */
    @Column(name = "appraise_id")
    private Long appraiseId;

    /**
     * 被评价人
     */
    @Column(name = "appraisee_id")
    private Long appraiseeId;

    /**
     * 评价人
     */
    @Column(name = "appraiser_id")
    private Long appraiserId;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 好友亲密度等级（1-5 共五档，用于控制评价字体大小）
     */
    @Column(name = "degree_of_intimacy")
    private Integer degreeOfIntimacy;

    /**
     * 状态  0-无效 1-有效
     */
    private Integer status;

    /**
     * 评论时间
     */
    @Column(name = "appraise_time")
    private Date appraiseTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

}