package com.alumni.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * description : 校友注册信息
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/7/7 23:55
 */
@Data
public class AlumniRegisterVo {

    @NotBlank(message = "姓名不能为空")
    @Length(max = 16, message = "姓名超过最大允许长度")
    @ApiModelProperty(value = "姓名")
    private String username;

    @NotBlank(message = "性别不能为空")
    @ApiModelProperty(value = "性别")
    private String gender;

    @NotBlank(message = "省不能为空")
    @ApiModelProperty(value = "省")
    private String province;

    @NotBlank(message = "市不能为空")
    @ApiModelProperty(value = "市")
    private String city;

    @NotBlank(message = "所在学校不能为空")
    @ApiModelProperty(value = "所在学校")
    private String collegeNo;

    @NotBlank(message = "培养单位不能为空")
    @ApiModelProperty(value = "培养单位")
    private String instituteNo;

    @NotBlank(message = "入学年级不能为空")
    @ApiModelProperty(value = "入学年级")
    private String grade;

    @NotBlank(message = "入学时间不能为空")
    @ApiModelProperty(value = "入学时间")
    private String entranceTime;

    @NotBlank(message = "毕业时间不能为空")
    @ApiModelProperty(value = "毕业时间")
    private String graduationTime;

    @NotBlank(message = "学位不能为空")
    @ApiModelProperty(value = "学位")
    private String degree;

    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phoneNo;

    @NotBlank(message = "微信号不能为空")
    @ApiModelProperty(value = "微信号")
    private String weixinNo;

    @NotBlank(message = "核心标签不能为空")
    @ApiModelProperty(value = "核心标签")
    private String coreLabel;

    @NotBlank(message = "所属行业不能为空")
    @ApiModelProperty(value = "所属行业")
    private String domain;

    @NotBlank(message = "专业不能为空")
    @ApiModelProperty(value = "专业")
    private String profession;

    @NotBlank(message = "认证方式不能为空")
    @ApiModelProperty(value = "认证方式")
    private String identifyType;

    @ApiModelProperty(value = "学号")
    private String studentNo;

    @ApiModelProperty(value = "身份证号")
    private String idCardNo;

    @ApiModelProperty(value = "工作经历-公司名称")
    private List<String> companyNameNor;

    @ApiModelProperty(value = "工作经历-职位名称")
    private List<String> positionNor;

    @ApiModelProperty(value = "工作经历-工作开始时间")
    private List<String> beginTimeNor;

    @ApiModelProperty(value = "工作经历-工作结束时间")
    private List<String> endTimeNor;

    @ApiModelProperty(value = "社会兼职-公司名称")
    private List<String> companyNamePT;

    @ApiModelProperty(value = "社会兼职-职位名称")
    private List<String> positionPT;

    @ApiModelProperty(value = "社会兼职-工作开始时间")
    private List<String> beginTimePT;

    @ApiModelProperty(value = "社会兼职-工作结束时间")
    private List<String> endTimePT;

}
