package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_circle_apply")
public class CircleApplyDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 入圈申请编号
     */
    @Column(name = "apply_circle_id")
    private Long applyCircleId;

    /**
     * 圈编号
     */
    @Column(name = "circle_no")
    private Long circleNo;

    /**
     * 圈名称
     */
    @Column(name = "circle_name")
    private String circleName;

    /**
     * 申请人
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 申请描述
     */
    @Column(name = "apply_content")
    private String applyContent;

    /**
     * 状态 1-已申请 2-已接收 3-已驳回
     */
    private Integer state;

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