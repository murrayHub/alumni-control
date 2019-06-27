package com.alumni.control.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 1.  公共请求类
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2018/9/4
 */
@Getter
@Setter
@ToString
@ApiModel
public class CommonParamDto {

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "忽略")
    private String userName;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "忽略")
    private String userId;

    /**
     * 学校编号
     */
    private String collegeNo;

    /**
     * tokenKey
     */
    private String tokenKey;

    /**
     * tokenVal
     */
    private String tokenVal;

    /**
     * 登录帐号
     */
    @ApiModelProperty(value = "忽略")
    private String loginNo;
}
