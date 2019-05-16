package com.techGoal.mapper;

import com.techGoal.pojo.dao.RegionDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 国家区域信息(分三级)
 */
public interface RegionMapper {
    /**
     * 按照<区域等级>查询
     *
     * @param level 区域等级
     * @return 结果集
     */
    List<RegionDo> selectProvinceByLevel(@Param("level") Integer level);

    /**
     * 按照省份代码查询下属城市
     *
     * @param proId 省份代码
     * @return 结果集
     */
    List<RegionDo> selectCityByProId(@Param("proId") Integer proId);

}