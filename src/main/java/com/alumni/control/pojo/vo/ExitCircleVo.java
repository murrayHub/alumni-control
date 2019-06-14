package com.alumni.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description : 退出圈子
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 16:35
 */
@Data
public class ExitCircleVo {

    @NotBlank(message = "用户编号不能为空")
    @ApiModelProperty(value = "用户编号")
    private String userId;

    @NotBlank(message = "圈编号不能为空")
    @ApiModelProperty(value = "圈编号")
    private String circleNo;

    @NotBlank(message = "操作人编号不能为空")
    @ApiModelProperty(value = "操作人编号")
    private String operatorNo;

}
