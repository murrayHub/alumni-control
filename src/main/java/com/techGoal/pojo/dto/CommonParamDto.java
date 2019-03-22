package com.techGoal.pojo.dto;

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
    private String memberName;

    /**
     * 商户号
     */
    @ApiModelProperty(value = "忽略")
    private Long memberId;

    /**
     * 操作员编号
     */
    @ApiModelProperty(value = "忽略")
    private Long operatorId;

    /**
     * 语言
     */
    @ApiModelProperty(value = "忽略")
    private String language;

    /**
     * tokenKey
     */
    private String tokenKey;

    /**
     * 登录帐号
     */
    @ApiModelProperty(value = "忽略")
    private String loginNo;
}
