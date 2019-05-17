package com.techGoal.pojo.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_job_position")
public class JobPositionDo {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 职位编号
     */
    @Column(name = "position_no")
    private Integer positionNo;

    /**
     * 职位名称
     */
    @Column(name = "position_name")
    private String positionName;

    /**
     * 父节点
     */
    @Column(name = "parent_no")
    private Integer parentNo;

}