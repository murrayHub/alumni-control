package com.alumni.control.service;


import com.alumni.control.pojo.dao.*;
import com.alumni.control.pojo.dto.SchoolProvinceDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description : 缓存工具服务-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/17 15:49
 */
public interface CacheService {

    /**
     * 获取所有省份集合
     *
     * @return 结果集
     */
    Map<String, RegionDo> getProvinces();

    /**
     * 获取所有省份集合
     *
     * @return 结果集
     */
    List<RegionDo> getProvinceList();

    /**
     * 获取城市集合
     *
     * @param proId 省份代码
     * @return 结果集
     */
    Map<String, RegionDo> getCitys(String proId);

    /**
     * 获取城市集合
     *
     * @param proId 省份代码
     * @return 结果集
     */
    List<RegionDo> getCityList(String proId);

    /**
     * 按照<省份代码>获取城市集合
     *
     * @param proId 省份代码
     * @return 结果集
     */
    Map<String, RegionDo> getCityByProId(String proId);

    /**
     * 获取高校所在省份集合
     *
     * @return 结果集
     */
    Set<SchoolProvinceDto> getAllSchoolProvince();

    /**
     * 根据根据省份Id获取高校信息
     *
     * @param proId 省份Id
     * @return 结果集
     */
    List<SchoolDo> getAllSchoolInfoByProId(String proId);

    /**
     * 获取所有一级行业信息
     *
     * @return 结果集
     */
    List<JobIndustryDo> getLevelOneIndustry();

    /**
     * 根据父节点获取所有二级行业信息
     *
     * @return 结果集
     */
    List<JobIndustryDo> getLevelTwoIndustry(String pid);

    /**
     * 获取所有一级职位信息
     *
     * @return 结果集
     */
    List<JobPositionDo> getLevelOnePosition();

    /**
     * 获取所有二级学院信息
     *
     * @return 结果集
     */
    List<UcasInstituteDo> getInstitutes();

    /**
     * 根据父节点获取所有二级职位信息
     *
     * @return 结果集
     */
    List<JobPositionDo> getLevelTwoPosition(String pid);

    /**
     * 获取所有用户标签集合
     *
     * @return 结果集
     */
    List<UserLabelDo> getUserLabels();

}
