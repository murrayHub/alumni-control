package com.techGoal.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * description : 圈主处理入圈申请记录
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 16:35
 */
@Data
public class HandleCircleApplyVo {

    @NotBlank(message = "入圈申请编号不能为空")
    @ApiModelProperty(value = "入圈申请编号")
    private String applyCircleId;

    @NotBlank(message = "圈编号不能为空")
    @ApiModelProperty(value = "圈编号")
    private String circleNo;

    @NotBlank(message = "申请状态不能为空")
    @ApiModelProperty(value = "申请状态")
    private String state;

    @NotBlank(message = "操作人编号不能为空")
    @ApiModelProperty(value = "操作人编号")
    private String operatorNo;

}
