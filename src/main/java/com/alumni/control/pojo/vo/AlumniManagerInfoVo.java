package com.alumni.control.pojo.vo;

import com.alumni.control.pojo.dao.UcasInstituteDo;
import com.alumni.control.utils.page.BasePage;
import lombok.Data;

import java.util.List;

@Data
public class AlumniManagerInfoVo extends BasePage {
    /**
     * 主键
     */
    private String id;

    /**
     * 认证编号
     */
    private String identifyCollegeId;

    /**
     * 认证方式
     */
    private String identifyType;

    /**
     * 认证状态： 0-未认证 1-二级认证待审核 2-二级认证初审通过 3-二级认证初审失败 4-二级认证复审通过 5-二级认证复审失败 6-一级认证待审核 7-一级认证初审通过 8-一级认证初审失败 9-一级认证复审通过 10-一级认证复审失败
     */
    private String identifyStatus;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 性别
     */
    private String genderType;

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
}