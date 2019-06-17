package com.alumni.control.mapper;

import com.alumni.control.pojo.dao.RegionDo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RegionMapper extends Mapper<RegionDo> {

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

    /**
     * 按照地区代码查询地区名称
     * @param id 地区代码
     * @return 结果集
     */
    RegionDo selectAreaById(@Param("id") Integer id);
}