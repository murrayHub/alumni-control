package com.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * description : 用户注册第二步
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 22:15
 */
@Data
public class UserInfoVo {

    @NotBlank(message = "用户编号不能为空")
    @ApiModelProperty(value = "用户编号")
    private String userId;

    @NotBlank(message = "用户姓名不能为空")
    @ApiModelProperty(value = "用户姓名")
    private String userRealName;

    @NotBlank(message = "性别不能为空")
    @ApiModelProperty(value = "性别：1-男 2-女")
    private String gender;

    @NotBlank(message = "省不能为空")
    @ApiModelProperty(value = "省")
    private String province;

    @NotBlank(message = "市不能为空")
    @ApiModelProperty(value = "市")
    private String city;

    @NotBlank(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phoneNo;

    @NotBlank(message = "微信号不能为空")
    @ApiModelProperty(value = "微信号")
    private String weixinNo;

    @NotBlank(message = "邮箱权限不能为空")
    @ApiModelProperty(value = "邮箱权限")
    private String emailAuth;

    @NotBlank(message = "手机号权限不能为空")
    @ApiModelProperty(value = "手机号权限")
    private String phoneNoAuth;

    @NotBlank(message = "微信号权限不能为空")
    @ApiModelProperty(value = "微信号权限")
    private String weixinNoAuth;

    @NotBlank(message = "核心标签不能为空")
    @ApiModelProperty(value = "核心标签")
    private String coreLabel;

    @NotBlank(message = "所属行业不能为空")
    @ApiModelProperty(value = "所属行业")
    private String domain;

    @ApiModelProperty(value = "头像图片")
    private String personImage;

    @ApiModelProperty(value = "主页图片")
    private String homeImage;

    @ApiModelProperty(value = "认证状态  0-未认证 1-待审核 2-已认证 3-认证失败")
    private String realnameStatus;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "更新时间")
    private Date updateAt;
}
