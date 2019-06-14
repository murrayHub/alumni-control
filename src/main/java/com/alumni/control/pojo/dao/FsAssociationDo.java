package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_fs_association")
public class FsAssociationDo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 联谊会编号
     */
    @Column(name = "fs_association_no")
    private Long fsAssociationNo;

    /**
     * 总会编号
     */
    @Column(name = "fs_headquarters_no")
    private Long fsHeadquartersNo;

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
     * 联谊会名称
     */
    @Column(name = "fs_association_name")
    private String fsAssociationName;

    /**
     * 联谊会简介
     */
    @Column(name = "fs_association_theme")
    private String fsAssociationTheme;

    /**
     * 联谊会标签
     */
    @Column(name = "fs_association_label")
    private String fsAssociationLabel;

    /**
     * 联谊会所在地(省)
     */
    @Column(name = "fs_association_location_prov")
    private Integer fsAssociationLocationProv;

    /**
     * 联谊会所在地(市)
     */
    @Column(name = "fs_association_location_city")
    private Integer fsAssociationLocationCity;

    /**
     * 联谊会头像
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