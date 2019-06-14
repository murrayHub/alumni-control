package com.alumni.control.pojo.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_school")
public class SchoolDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学校编号
     */
    @Column(name = "school_id")
    private String schoolId;

    /**
     * 学校名称
     */
    @Column(name = "school_name")
    private String schoolName;

    /**
     * 省份编号
     */
    @Column(name = "province_id")
    private String provinceId;

    /**
     * 省份名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 城市编号
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 高校级别
     */
    private String level;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 其他
     */
    private String other;

}