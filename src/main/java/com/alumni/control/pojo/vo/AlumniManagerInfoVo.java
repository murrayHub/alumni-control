package com.alumni.control.pojo.vo;

import lombok.Data;

@Data
public class AlumniManagerInfoVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userRealName;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phoneNo;

    /**
     * 微信号
     */
    private String weixinNo;

    /**
     * 管理员编号
     */
    private String managerId;

    /**
     * 学校代码
     */
    private String collegeNo;

    /**
     * 学院代码
     */
    private String instituteNo;

    /**
     * 注册邀请码（防止虚假用户随意注册，一级二级单位不同类型操作员注册码不同，平台会校验）
     */
    private String invitationCode;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 年级
     */
    private String grade;

    /**
     * 学位类别
     */
    private String degreeType;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页记录数
     */
    private Integer pageSize;

}