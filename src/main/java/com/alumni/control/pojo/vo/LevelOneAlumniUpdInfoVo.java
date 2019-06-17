package com.alumni.control.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 20:12
 */
@Data
public class LevelOneAlumniUpdInfoVo {
    /**
     * 管理员编号
     */
    private String managerId;
    /**
     * 认证编号
     */
    private String identifyCollegeId;

    /**
     * 用户姓名
     */
    private String userRealName;

    /**
     * 性别：1-男 2-女
     */
    private String gender;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生日期
     */
    private String birthDate;

    /**
     * 证件号码（身份证或护照）
     */
    private String idCard;

    /**
     * 原证件号码
     */
    private String originalIdCard;

    /**
     * 培养层次 1-本科生 2-硕士研究生 3-博士研究生
     */
    private String trainingLevel;

    /**
     * 录取单位
     */
    private String admissionUnit;

    /**
     * 管理单位
     */
    private String manageUnit;

    /**
     * 培养单位
     */
    private String trainingUnit;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 新学号
     */
    private String newStudentNo;

    /**
     * 入学时间
     */
    private String entranceTime;

    /**
     * 毕业时间
     */
    private String graduationTime;

    /**
     * 导师
     */
    private String tutorName;

    /**
     * 专业
     */
    private String majorName;

    /**
     * 学生状态
     */
    private String studentStatus;

    /**
     * 工作状态
     */
    private String jobStatus;

    /**
     * 国家
     */
    private String country;

    /**
     * 省(州) 市
     */
    private String city;

    /**
     * 所在区域
     */
    private String area;

    /**
     * 工作单位
     */
    private String employer;

    /**
     * 单位性质
     */
    private String unitNature;

    /**
     * 专家类别
     */
    private String expertCategory;

    /**
     * 专业技术职务
     */
    private String specializedTechnicalJob;

    /**
     * 职务
     */
    private String position;

    /**
     * 通讯地址
     */
    private String mailingAddress;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 联系电话
     */
    private String phoneNo;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    private String weixinNo;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private String createAt;

    /**
     * 更新时间
     */
    private String updateAt;
    /**
     * 学校代码
     */
    private String collegeNo;
    /**
     * 认证状态： 0-未认证 1-二级认证待审核 2-二级认证初审通过 3-二级认证初审失败 4-二级认证复审通过 5-二级认证复审失败 6-一级认证待审核 7-一级认证初审通过 8-一级认证初审失败 9-一级认证复审通过 10-一级认证复审失败
     */
    private String identifyStatus;
}
