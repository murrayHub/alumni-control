package com.alumni.control.pojo.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "t_alumni_manager_info")
public class AlumniManagerInfoDo {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户姓名
     */
    @Column(name = "user_real_name")
    private String userRealName;

    /**
     * 省
     */
    private Integer province;

    /**
     * 市
     */
    private Integer city;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    @Column(name = "phone_no")
    private String phoneNo;

    /**
     * 微信号
     */
    @Column(name = "weixin_no")
    private String weixinNo;

    /**
     * 管理员级别  1：一级单位 2：二级单位
     */
    @Column(name = "manager_level")
    private Integer managerLevel;

    /**
     * 管理员类型 1：主操作员 2：从操作员
     */
    @Column(name = "manager_type")
    private Integer managerType;

    /**
     * 学校代码
     */
    @Column(name = "college_no")
    private String collegeNo;

    /**
     * 学院代码
     */
    @Column(name = "institute_no")
    private String instituteNo;

    /**
     * 注册邀请码（防止虚假用户随意注册，一级二级单位不同类型操作员注册码不同，平台会校验）
     */
    @Column(name = "invitation_code")
    private String invitationCode;

}