package com.alumni.control.pojo.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_region")
public class RegionDo {
    /**
     * 区域主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 区域上级标识
     */
    private Integer pid;

    /**
     * 地名简称
     */
    private String sname;

    /**
     * 区域等级
     */
    private Integer level;

    /**
     * 区域编码
     */
    private String citycode;

    /**
     * 邮政编码
     */
    private String yzcode;

    /**
     * 组合名称
     */
    private String mername;

    @Column(name = "Lng")
    private Float lng;

    @Column(name = "Lat")
    private Float lat;

    private String pinyin;

}