package com.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    @ApiModelProperty(value = "账号")
    private String loginNo;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String pwd;

    @NotBlank(message = "账号类型不能为空")
    @ApiModelProperty(value = "账号类型")
    private String loginType;
}
