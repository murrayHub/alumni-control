package com.alumni.control.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * description : 入圈申请
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 14:28
 */
@Data
public class CircleApplyVo {

    @NotBlank(message = "入圈申请编号不能为空")
    @ApiModelProperty(value = "入圈申请编号")
    private String applyCircleId;

    @NotBlank(message = "圈编号不能为空")
    @ApiModelProperty(value = "圈编号")
    private String circleNo;

    @NotBlank(message = "圈名称不能为空")
    @ApiModelProperty(value = "圈名称")
    private String circleName;

    @NotBlank(message = "申请人不能为空")
    @ApiModelProperty(value = "申请人")
    private String applyUserId;

    @NotBlank(message = "申请描述不能为空")
    @ApiModelProperty(value = "申请描述")
    private String applyContent;

    @ApiModelProperty(value = "申请状态")
    private String state;

    @ApiModelProperty(value = "申请时间")
    private Date createAt;

}
