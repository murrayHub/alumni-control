package com.techGoal.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Table(name = "t_attention_relation")
public class AttentionRelationVo {

    @NotBlank(message = "用户编号不能为空")
    @ApiModelProperty(value = "用户编号")
    private String userId;

    @NotBlank(message = "目标对象不能为空")
    @ApiModelProperty(value = "目标对象")
    private String targetObject;

    /**
     * 关系类型：当目标对象为关注者，标示为1；当目标对象为被关注者，标示为2；当双方互相关注，标示为3
     */
    @ApiModelProperty(value = "关系类型")
    private String relationType;

    /**
     * 操作类型：1-加关注 2-取消关注
     */
    @NotBlank(message = "操作类型不能为空")
    @ApiModelProperty(value = "操作类型")
    private String operateType;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

}