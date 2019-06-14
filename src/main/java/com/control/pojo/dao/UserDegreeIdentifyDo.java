package com.techGoal.pojo.dao;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 学院名称
     */
    @Column(name = "institute_name")
    private String instituteName;

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

}