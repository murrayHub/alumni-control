package com.alumni.control.pojo.vo;

import lombok.Data;

@Data
public class SchoolVo {

    /**
     * 学校编号
     */
    private String schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 省份编号
     */
    private String provinceId;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 城市编号
     */
    private String cityId;

    /**
     * 城市名称
     */
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