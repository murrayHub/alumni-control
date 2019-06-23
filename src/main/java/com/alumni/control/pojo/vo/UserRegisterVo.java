package com.alumni.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * description : 用户注册
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 22:08
 */
@Data
public class UserRegisterVo {
    @NotBlank(message = "账号不能为空")
    @Length(max = 64, message = "账号超过最大允许长度")
    @ApiModelProperty(value = "账号")
    private String accountNo;

    @NotBlank(message = "用户姓名不能为空")
    @Length(max = 16, message = "用户姓名超过最大允许长度")
    @ApiModelProperty(value = "用户姓名")
    private String username;

    @NotBlank(message = "所在学校不能为空")
    @ApiModelProperty(value = "所在学校")
    private String college;

    @ApiModelProperty(value = "所在学院")
    private String institute;

    @NotBlank(message = "注册邀请码不能为空")
    @ApiModelProperty(value = "注册邀请码")
    private String invitation;

    @NotBlank(message = "密码不能为空")
    @Length(max = 64, message = "密码超过最大允许长度")
    @ApiModelProperty(value = "密码")
    private String pass;

    @NotBlank(message = "确认密码不能为空")
    @Length(max = 64, message = "确认密码超过最大允许长度")
    @ApiModelProperty(value = "确认密码")
    private String checkPass;

    @ApiModelProperty(value = "token令牌")
    private String token;
}
