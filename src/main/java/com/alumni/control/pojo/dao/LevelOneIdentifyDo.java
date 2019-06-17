package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_level_one_identify")
public class LevelOneIdentifyDo {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 认证编号
     */
    @Column(name = "identify_college_id")
    private Long identifyCollegeId;

    /**
     * 用户姓名
     */
    @Column(name = "user_real_name")
    private String userRealName;

    /**
     * 性别：1-男 2-女
     */
    private Integer gender;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生日期
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * 证件号码（身份证或护照）
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 原证件号码
     */
    @Column(name = "original_id_card")
    private String originalIdCard;

    /**
     * 培养层次 1-本科生 2-硕士研究生 3-博士研究生
     */
    @Column(name = "training_level")
    private Integer trainingLevel;

    /**
     * 录取单位
     */
    @Column(name = "admission_unit")
    private String admissionUnit;

    /**
     * 管理单位
     */
    @Column(name = "manage_unit")
    private String manageUnit;

    /**
     * 培养单位
     */
    @Column(name = "training_unit")
    private String trainingUnit;

    /**
     * 学号
     */
    @Column(name = "student_no")
    private String studentNo;

    /**
     * 新学号
     */
    @Column(name = "new_student_no")
    private String newStudentNo;

    /**
     * 入学时间
     */
    @Column(name = "entrance_time")
    private Date entranceTime;

    /**
     * 毕业时间
     */
    @Column(name = "graduation_time")
    private Date graduationTime;

    /**
     * 导师
     */
    @Column(name = "tutor_name")
    private String tutorName;

    /**
     * 专业
     */
    @Column(name = "major_name")
    private String majorName;

    /**
     * 学生状态
     */
    @Column(name = "student_status")
    private String studentStatus;

    /**
     * 工作状态
     */
    @Column(name = "job_status")
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
    @Column(name = "unit_nature")
    private String unitNature;

    /**
     * 专家类别
     */
    @Column(name = "expert_category")
    private String expertCategory;

    /**
     * 专业技术职务
     */
    @Column(name = "specialized_technical_job")
    private String specializedTechnicalJob;

    /**
     * 职务
     */
    private String position;

    /**
     * 通讯地址
     */
    @Column(name = "mailing_address")
    private String mailingAddress;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 联系电话
     */
    @Column(name = "phone_no")
    private String phoneNo;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微信
     */
    @Column(name = "weixin_no")
    private String weixinNo;

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
    /**
     * 学校代码
     */
    @Transient
    private String collegeNo;
    /**
     * 认证状态： 0-未认证 1-二级认证待审核 2-二级认证初审通过 3-二级认证初审失败 4-二级认证复审通过 5-二级认证复审失败 6-一级认证待审核 7-一级认证初审通过 8-一级认证初审失败 9-一级认证复审通过 10-一级认证复审失败
     */
    @Transient
    private Integer identifyStatus;

}