package com.techGoal.pojo.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class SchoolProvinceDto {

    /**
     * 省份编号
     */
    private String provinceId;

    /**
     * 省份名称
     */
    private String provinceName;


}