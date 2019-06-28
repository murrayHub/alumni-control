package com.alumni.control.service.impl;

import com.alumni.control.dict.NumberDict;
import com.alumni.control.mapper.*;
import com.alumni.control.pojo.dao.*;
import com.alumni.control.pojo.dto.SchoolProvinceDto;
import com.alumni.control.service.CacheService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
 * @date : 2019/5/16 21:38
 */
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {
    /**
     * 省份-集合
     */
    private static final Map<String, RegionDo> provinceMap = Maps.newConcurrentMap();
    /**
     * 省份-集合
     */
    private static final List<RegionDo> provinceList = Lists.newArrayList();
    /**
     * 所有城市-集合
     */
    private static final Map<String, Map<String, RegionDo>> allCitys = Maps.newConcurrentMap();

    /**
     * 所有高校所在省份
     */
    private static final Set<SchoolProvinceDto> provinceCacheSet = Sets.newConcurrentHashSet();

    /**
     * 一级行业集合
     */
    private static List<JobIndustryDo> levelOneIndustryList = Lists.newArrayList();

    /**
     * 一级职位集合
     */
    private static List<JobPositionDo> levelOnePositionList = Lists.newArrayList();

    /**
     * 获取学院集合
     */
    private static List<UcasInstituteDo> ucasInstituteDoList = Lists.newArrayList();

    /**
     * 用户标签集合
     */
    private static List<UserLabelDo> userLabelList = Lists.newArrayList();

    /**
     * 全国区域信息
     */
    @Autowired
    private RegionMapper regionMapper;
    /**
     * 全国高校信息
     */
    @Autowired
    private SchoolMapper schoolMapper;
    /**
     * 用户标签集合 Mapper
     */
    @Autowired
    private UserLabelMapper userLabelMapper;

    /**
     * 行业信息
     */
    @Autowired
    private JobIndustryMapper jobIndustryMapper;

    /**
     * 职位信息
     */
    @Autowired
    private JobPositionMapper jobPositionMapper;
    /**
     * 二级学院信息
     */
    @Autowired
    private UcasInstituteDoMapper ucasInstituteDoMapper;

    // @PostConstruct
    public void initCacheData() {
        log.info("加载缓存-国家区域信息-开始");
        Map<String, RegionDo> provinceMaps = getProvinces();
        for (Map.Entry<String, RegionDo> entry : provinceMaps.entrySet()) {
            allCitys.put(entry.getKey(), getCitys(entry.getKey()));
        }
        log.info("加载缓存-国家区域信息-结束");
        log.info("加载缓存-高校所在省份集合-开始");
        getAllSchoolProvince();
        log.info("加载缓存-高校所在省份集合-结束");
        log.info("加载缓存-获取所有一级行业信息-开始");
        getLevelOneIndustry();
        log.info("加载缓存-获取所有一级行业信息-结束");
        log.info("加载缓存-获取所有一级职位信息-开始");
        getLevelOnePosition();
        log.info("加载缓存-获取所有一级职位信息-结束");
        log.info("加载缓存-获取所有用户标签集合-开始");
        getUserLabels();
        log.info("加载缓存-获取所有用户标签集合-结束");
        log.info("加载缓存-获取所有二级学院信息-开始");
        getInstitutes();
        log.info("加载缓存-获取所有二级学院信息-结束");
        log.info("加载缓存-获取所有省份信息-开始");
        getProvinceList();
        log.info("加载缓存-获取所有省份信息-结束");
    }


    /**
     * 按照<区域等级>查询
     *
     * @param level 区域等级
     * @return 结果集
     */
    public List<RegionDo> selectRegionByLevel(Integer level) {
        List<RegionDo> levelOneList = regionMapper.selectProvinceByLevel(level);
        return levelOneList;
    }

    /**
     * 获取所有省份集合
     *
     * @return 结果集
     */
    @Override
    public Map<String, RegionDo> getProvinces() {
        if (provinceMap.size() == NumberDict.ZERO) {
            List<RegionDo> provinceList = regionMapper.selectProvinceByLevel(NumberDict.ONE);
            for (RegionDo regionDo : provinceList) {
                provinceMap.put(String.valueOf(regionDo.getId()), regionDo);
            }
        }
        return provinceMap;
    }

    /**
     * 获取所有省份集合
     *
     * @return 结果集
     */
    @Override
    public List<RegionDo> getProvinceList() {
        if (provinceList.size() == NumberDict.ZERO) {
            List<RegionDo> provinceList = regionMapper.selectProvinceByLevel(NumberDict.ONE);
            return provinceList;
        }
        return provinceList;
    }

    /**
     * 获取城市集合
     *
     * @param proId 省份代码
     * @return 结果集
     */
    @Override
    public Map<String, RegionDo> getCitys(String proId) {
        Map<String, RegionDo> cityMap = Maps.newConcurrentMap();
        List<RegionDo> cityList = regionMapper.selectCityByProId(Integer.valueOf(proId));
        for (RegionDo regionDo : cityList) {
            cityMap.put(String.valueOf(regionDo.getId()), regionDo);
        }
        return cityMap;
    }

    /**
     * 获取城市集合
     *
     * @param proId 省份代码
     * @return 结果集
     */
    @Override
    public List<RegionDo> getCityList(String proId) {
        List<RegionDo> cityList = regionMapper.selectCityByProId(Integer.valueOf(proId));
        return cityList;
    }

    /**
     * 按照<省份代码>获取城市集合
     *
     * @param proId 省份代码
     * @return 结果集
     */
    @Override
    public Map<String, RegionDo> getCityByProId(String proId) {
        Map<String, RegionDo> cityMap = Maps.newConcurrentMap();
        if (allCitys.size() == NumberDict.ZERO) {
            List<RegionDo> cityList = regionMapper.selectCityByProId(Integer.valueOf(proId));
            for (RegionDo regionDo : cityList) {
                cityMap.put(String.valueOf(regionDo.getId()), regionDo);
            }
        } else {
            for (Map.Entry<String, Map<String, RegionDo>> entry : allCitys.entrySet()) {
                if (entry.getKey().equals(proId)) {
                    for (Map.Entry<String, RegionDo> entry1 : entry.getValue().entrySet()) {
                        cityMap.put(entry1.getKey(), entry1.getValue());
                    }
                }
            }
        }
        return cityMap;
    }

    /**
     * 获取高校所在省份集合
     *
     * @return 结果集
     */
    @Override
    public Set<SchoolProvinceDto> getAllSchoolProvince() {
        if (provinceCacheSet.size() == NumberDict.ZERO) {
            Set<String> provinceSet = Sets.newConcurrentHashSet();
            List<SchoolDo> schoolDoList = schoolMapper.selectAll();
            for (SchoolDo schoolDo : schoolDoList) {
                provinceSet.add(schoolDo.getProvinceId());
            }
            for (String proId : provinceSet) {
                SchoolDo schoolDo = new SchoolDo();
                schoolDo.setProvinceId(proId);
                List<SchoolDo> schoolList = schoolMapper.select(schoolDo);
                if (!CollectionUtils.isEmpty(schoolList)) {
                    SchoolDo schoolDo1 = schoolList.get(NumberDict.ZERO);
                    SchoolProvinceDto schoolProvinceDto = new SchoolProvinceDto();
                    schoolProvinceDto.setProvinceId(schoolDo1.getProvinceId());
                    schoolProvinceDto.setProvinceName(schoolDo1.getProvinceName());
                    provinceCacheSet.add(schoolProvinceDto);
                }
            }
        }
        return provinceCacheSet;
    }


    /**
     * 根据根据省份Id获取高校信息
     *
     * @param proId 省份Id
     * @return 结果集
     */
    @Override
    public List<SchoolDo> getAllSchoolInfoByProId(String proId) {
        SchoolDo schoolDo = new SchoolDo();
        schoolDo.setProvinceId(proId);
        List<SchoolDo> schoolDoList = schoolMapper.select(schoolDo);
        return schoolDoList;
    }

    /**
     * 获取所有一级行业信息
     *
     * @return 结果集
     */
    @Override
    public List<JobIndustryDo> getLevelOneIndustry() {
        if (levelOneIndustryList.size() == NumberDict.ZERO) {
            JobIndustryDo jobIndustryDo = new JobIndustryDo();
            jobIndustryDo.setParentNo(NumberDict.ZERO);
            levelOneIndustryList = jobIndustryMapper.select(jobIndustryDo);
        }
        return levelOneIndustryList;
    }

    /**
     * 根据父节点获取所有二级行业信息
     *
     * @return 结果集
     */
    @Override
    public List<JobIndustryDo> getLevelTwoIndustry(String pid) {
        JobIndustryDo jobIndustryDo = new JobIndustryDo();
        jobIndustryDo.setParentNo(Integer.valueOf(pid));
        return jobIndustryMapper.select(jobIndustryDo);
    }

    /**
     * 获取所有一级职位信息
     *
     * @return 结果集
     */
    @Override
    public List<JobPositionDo> getLevelOnePosition() {
        if (levelOnePositionList.size() == NumberDict.ZERO) {
            JobPositionDo jobPositionDo = new JobPositionDo();
            jobPositionDo.setParentNo(NumberDict.ZERO);
            levelOnePositionList = jobPositionMapper.select(jobPositionDo);
        }
        return levelOnePositionList;
    }

    /**
     * 获取所有二级学院信息
     *
     * @return 结果集
     */
    @Override
    public List<UcasInstituteDo> getInstitutes() {
        if (ucasInstituteDoList.size() == NumberDict.ZERO) {
            ucasInstituteDoList = ucasInstituteDoMapper.selectAll();
        }
        return ucasInstituteDoList;
    }

    /**
     * 根据父节点获取所有二级职位信息
     *
     * @return 结果集
     */
    @Override
    public List<JobPositionDo> getLevelTwoPosition(String pid) {
        JobPositionDo jobPositionDo = new JobPositionDo();
        jobPositionDo.setParentNo(Integer.valueOf(pid));
        return jobPositionMapper.select(jobPositionDo);
    }

    /**
     * 获取所有用户标签集合
     *
     * @return 结果集
     */
    @Override
    public List<UserLabelDo> getUserLabels() {
        if (userLabelList.size() == NumberDict.ZERO) {
            UserLabelDo userLabelDo = new UserLabelDo();
            userLabelDo.setStatus(NumberDict.ONE);
            userLabelList = userLabelMapper.select(userLabelDo);
        }
        return userLabelList;
    }

}
