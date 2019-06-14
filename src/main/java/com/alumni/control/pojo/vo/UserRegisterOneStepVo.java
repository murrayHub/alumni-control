package com.alumni.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * description : 用户注册第一步
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 20:49
 */
@Data
public class UserRegisterOneStepVo {

    @NotBlank(message = "账号不能为空")
    @Length(max = 64, message = "账号超过最大允许长度")
    @ApiModelProperty(value = "账号")
    private String loginNo;

    @NotBlank(message = "密码不能为空")
    @Length(max = 64, message = "密码超过最大允许长度")
    @ApiModelProperty(value = "密码")
    private String pwd;

    @NotBlank(message = "账号类型不能为空")
    @Range(min = 1,max = 2,message = "不支持的账号类型")
    @ApiModelProperty(value = "登录账号类型：1-手机号 2-邮箱")
    private String loginType;
}
