package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_association")
public class AssociationDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 协会编号
     */
    @Column(name = "association_no")
    private Long associationNo;

    /**
     * 总会编号
     */
    @Column(name = "headquarters_no")
    private Long headquartersNo;

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
     * 协会名称
     */
    @Column(name = "association_name")
    private String associationName;

    /**
     * 协会简介
     */
    @Column(name = "association_theme")
    private String associationTheme;

    /**
     * 协会标签
     */
    @Column(name = "association_label")
    private String associationLabel;

    /**
     * 协会所在地(省)
     */
    @Column(name = "association_location_prov")
    private Integer associationLocationProv;

    /**
     * 协会所在地(市)
     */
    @Column(name = "association_location_city")
    private Integer associationLocationCity;

    /**
     * 协会头像
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