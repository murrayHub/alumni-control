package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_headquarters")
public class HeadquartersDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 总会编号
     */
    @Column(name = "headquarters_no")
    private Long headquartersNo;

    /**
     * 学校编号
     */
    @Column(name = "college_no")
    private Long collegeNo;

    /**
     * 超级管理员
     */
    @Column(name = "association_sp_admin_no")
    private Long associationSpAdminNo;

    /**
     * 管理员
     */
    @Column(name = "association_admin_no")
    private Long associationAdminNo;

    /**
     * 总会名称
     */
    @Column(name = "headquarters_name")
    private String headquartersName;

    /**
     * 总会简介
     */
    @Column(name = "headquarters_theme")
    private String headquartersTheme;

    /**
     * 总会头像
     */
    private String image;

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