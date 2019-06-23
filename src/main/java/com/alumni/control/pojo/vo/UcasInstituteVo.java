package com.alumni.control.pojo.vo;

import lombok.Data;

@Data
public class UcasInstituteVo {

    /**
     * 学院编号
     */
    private String instituteNo;

    /**
     * 学院名称
     */
    private String instituteName;

    /**
     * 状态 0-失效 1-有效
     */
    private String enabled;

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
    private String createAt;

    /**
     * 更新时间
     */
    private String updateAt;

    /**
     * 单位所在地
     */
    private String location;

}