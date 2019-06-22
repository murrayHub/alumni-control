package com.alumni.control.pojo.vo;

import com.alumni.control.pojo.dao.UcasInstituteDo;
import com.alumni.control.pojo.dto.JobsInfoDto;
import com.alumni.control.pojo.dto.PartTimeJobsDto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.soap.SAAJResult;
import java.util.Date;
import java.util.List;

@Data
public class UserDegreeIdentifyVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 认证编号
     */
    private String identifyCollegeId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 学校代码
     */
    private String collegeNo;

    /**
     * 学校名称
     */
    private String collegeName;

    /**
     * 学院编号
     */
    private String instituteNo;

    /**
     * 学院名称
     */
    private String instituteName;

    /**
     * 入学时间
     */
    private String entranceTime;

    /**
     * 毕业时间
     */
    private String graduationTime;

    /**
     * 学位类别编号 1-学士 2-硕士 3-博士 4-其他
     */
    private String degreeNo;

    /**
     * 学位类别编号 1-学士 2-硕士 3-博士 4-其他
     */
    private String degreeName;

    /**
     * 专业名称
     */
    private String professionName;

    /**
     * 状态 0-失效 1-有效 
     */
    private String enabled;

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
     * 认证状态： 0-未认证 1-二级认证待审核 2-二级认证初审通过 3-二级认证初审失败 4-二级认证复审通过 5-二级认证复审失败 6-一级认证待审核 7-一级认证初审通过 8-一级认证初审失败 9-一级认证复审通过 10-一级认证复审失败
     */
    private String identifyStatus;

    /**
     * 认证状态： 0-未认证 1-二级认证待审核 2-二级认证初审通过 3-二级认证初审失败 4-二级认证复审通过 5-二级认证复审失败 6-一级认证待审核 7-一级认证初审通过 8-一级认证初审失败 9-一级认证复审通过 10-一级认证复审失败
     */
    private String identifyStatusStr;

    /**
     * 学号
     */
    private String studentNo;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 认证方式  1-身份证号 2-学号
     */
    private String identifyType;
    /**
     * 认证方式  1-身份证号 2-学号
     */
    private String identifyTypeValue;

    /**
     * 年级
     */
    private String grade;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 性别
     */
    private String gender;
    /**
     * 性别
     */
    private String genderValue;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;

    /**
     * 工作单位信息
     */
    private List<JobsInfoDto> jobsInfoDto;

    /**
     * 单位信息
     */
    private List<PartTimeJobsDto> partTimeJobsDto;

    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 二级学院列表
     */
    private List<UcasInstituteDo> ucasInstituteDoList;

}