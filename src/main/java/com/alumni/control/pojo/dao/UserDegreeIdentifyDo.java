package com.alumni.control.pojo.dao;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_user_degree_identify")
public class UserDegreeIdentifyDo {
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
     * 用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 学校代码
     */
    @Column(name = "college_no")
    private String collegeNo;

    /**
     * 学院编号
     */
    @Column(name = "institute_no")
    private Long instituteNo;

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
     * 学位类别编号 1-学士 2-硕士 3-博士 4-其他
     */
    @Column(name = "degree_no")
    private Integer degreeNo;

    /**
     * 专业名称
     */
    @Column(name = "profession_name")
    private String professionName;

    /**
     * 状态 0-失效 1-有效 
     */
    private Integer enabled;

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
     * 认证状态： 0-未认证 1-二级认证待审核 2-二级认证初审通过 3-二级认证初审失败 4-二级认证复审通过 5-二级认证复审失败 6-一级认证待审核 7-一级认证初审通过 8-一级认证初审失败 9-一级认证复审通过 10-一级认证复审失败
     */
    @Column(name = "identify_status")
    private Integer identifyStatus;

    /**
     * 学号
     */
    @Column(name = "student_no")
    private String studentNo;

    /**
     * 身份证号
     */
    @Column(name = "id_card_no")
    private String idCardNo;

    /**
     * 认证方式  1-身份证号 2-学号
     */
    @Column(name = "identify_type")
    private Integer identifyType;

    /**
     * 年级
     */
    private String grade;

    /**
     * 学生姓名
     */
    @Transient
    private String studentName;
    /**
     * 性别
     */
    @Transient
    private Integer gender;
    /**
     * 省
     */
    @Transient
    private Integer province;
    /**
     * 市
     */
    @Transient
    private Integer city;

    /**
     * 当前页
     */
    @Transient
    private Integer currentPage;
    /**
     * 每页记录数
     */
    @Transient
    private Integer pageSize;
}