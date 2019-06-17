package com.alumni.control.pojo.dto;

import lombok.Data;

/**
 * description : 工作单位信息
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 10:47
 */
@Data
public class PartTimeJobsDto {
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 工作开始时间
     */
    private String workStartTime;

    /**
     * 工作结束时间
     */
    private String workEndTime;

    /**
     * 职位名称
     */
    private String positionName;
}
