package com.alumni.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * description : 用户登录
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 22:08
 */
@Data
public class UserLoginVo {
    @NotBlank(message = "账号不能为空")
    @Length(max = 64, message = "账号超过最大允许长度")
    @ApiModelProperty(value = "账号")
    private String loginNo;

    @NotBlank(message = "密码不能为空")
    @Length(max = 64, message = "密码超过最大允许长度")
    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "token令牌")
    private String token;
}
