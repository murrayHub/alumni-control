package com.alumni.control.pojo.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_job_industry")
public class JobIndustryDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 行业编号
     */
    @Column(name = "industry_no")
    private Integer industryNo;

    /**
     * 行业名称
     */
    @Column(name = "industry_name")
    private String industryName;

    /**
     * 父级编号
     */
    @Column(name = "parent_no")
    private Integer parentNo;

    @Transient
    private String subIndustries;

}