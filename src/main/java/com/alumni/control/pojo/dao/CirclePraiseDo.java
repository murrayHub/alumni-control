package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_circle_praise")
public class CirclePraiseDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 点赞编号
     */
    @Column(name = "praise_id")
    private Long praiseId;

    /**
     * 动态编号
     */
    @Column(name = "moments_id")
    private Long momentsId;

    /**
     * 点赞者
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 点赞类型 1-动态朋友圈 2-公告 3-活动
     */
    @Column(name = "praise_type")
    private Integer praiseType;

    /**
     * 状态  0-未点赞  1-已点赞
     */
    private Integer status;

    /**
     * 点赞时间
     */
    @Column(name = "praise_time")
    private Date praiseTime;

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