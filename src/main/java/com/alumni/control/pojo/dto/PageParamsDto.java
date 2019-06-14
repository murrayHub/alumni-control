package com.alumni.control.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description : 分页查询请求参数
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/4/28 14:40
 */
@Data
public class PageParamsDto {
    @ApiModelProperty(value = "当前页", example = "1")
    private Integer currentPage;

    @ApiModelProperty(value = "查询条数", example = "5")
    private Integer pageSize;
}
