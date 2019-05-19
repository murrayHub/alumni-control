package com.techGoal.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_circle_moments")
public class CircleMomentsDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 动态编号
     */
    @Column(name = "moments_id")
    private Long momentsId;

    /**
     * 圈编号
     */
    @Column(name = "circle_no")
    private Long circleNo;

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
    private Byte status;

    /**
     * 评论权限 1:允许评论,0:不允许评论
     */
    @Column(name = "comment_flag")
    private Byte commentFlag;

    /**
     * 发布位置
     */
    private String location;

    /**
     * 发布人
     */
    @Column(name = "publisher_id")
    private Long publisherId;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 发布时间
     */
    @Column(name = "publisher_time")
    private Date publisherTime;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

}