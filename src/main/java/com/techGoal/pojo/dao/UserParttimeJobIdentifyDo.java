package com.techGoal.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_user_parttime_job_identify")
public class UserParttimeJobIdentifyDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 认证编号
     */
    @Column(name = "identify_parttime_job_id")
    private Long identifyParttimeJobId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 工作开始时间
     */
    @Column(name = "work_start_time")
    private Date workStartTime;

    /**
     * 工作结束时间
     */
    @Column(name = "work_end_time")
    private Date workEndTime;

    /**
     * 职位名称
     */
    @Column(name = "position_name")
    private String positionName;

    /**
     * 状态  0-失效 1-有效
     */
    private Byte enabled;

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