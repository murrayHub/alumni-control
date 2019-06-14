package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_moments_time_axis")
public class MomentsTimeAxisDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 动态编号
     */
    @Column(name = "moments_id")
    private Long momentsId;

    /**
     * 圈编号（非来自圈子发布，该字段默认为0）
     */
    @Column(name = "circle_no")
    private Long circleNo;

    /**
     * 是否本人发布：0-否 1-是
     */
    @Column(name = "is_own")
    private Integer isOwn;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}