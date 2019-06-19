package com.alumni.control.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description : 校友信息修改
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 16:57
 */
@Data
public class AlumniInfoUpdVo {
    /**
     * 认证编号
     */
    @NotBlank(message = "认证编号不能为空")
    private String identifyCollegeId;
    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 年级
     */
    private String grade;

    /**
     * 专业名称
     */
    private String professionName;

    /**
     * 学位类别
     */
    private String degreeType;
    /**
     * 学院代码
     */
    @NotBlank(message = "学院代码不能为空")
    private String instituteNo;
    /**
     * 学校代码
     */
    private String collegeNo;
    /**
     * 认证状态： 0-未认证 1-二级认证待审核 2-二级认证初审通过 3-二级认证初审失败 4-二级认证复审通过 5-二级认证复审失败 6-一级认证待审核 7-一级认证初审通过 8-一级认证初审失败 9-一级认证复审通过 10-一级认证复审失败
     */
    @NotBlank(message = "认证状态不能为空")
    private String identifyStatus;
    /**
     * 学号
     */
    private String studentNo;
    /**
     * 入学时间
     */
    private String entranceTime;

    /**
     * 毕业时间
     */
    private String graduationTime;
    /**
     * 管理员编号
     */
    @NotBlank(message = "管理员编号不能为空")
    private String managerId;

}
