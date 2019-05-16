package com.techGoal.service.impl;

import com.google.common.collect.Maps;
import com.techGoal.dict.NumberDict;
import com.techGoal.mapper.RegionMapper;
import com.techGoal.pojo.dao.RegionDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

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
public class CacheServiceImpl {
    @Autowired
    private RegionMapper regionMapper;

    @PostConstruct
    public void cacheRegionData() {
        log.info("加载缓存-国家区域信息-开始");
        Map<String, RegionDo> provinceMaps = getProvinces();
        for (Map.Entry<String, RegionDo> entry : provinceMaps.entrySet()) {
            allCitys.put(entry.getKey(), getCitys(entry.getKey()));
        }
        log.info("加载缓存-国家区域信息-结束");
    }


    /**
     * 省份-集合
     */
    private static final Map<String, RegionDo> provinceMap = Maps.newConcurrentMap();
    /**
     * 所有城市-集合
     */
    private static final Map<String, Map<String, RegionDo>> allCitys = Maps.newConcurrentMap();

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
     * 获取城市集合
     *
     * @param proId 省份代码
     * @return 结果集
     */
    public Map<String, RegionDo> getCitys(String proId) {
        Map<String, RegionDo> cityMap = Maps.newConcurrentMap();
        List<RegionDo> cityList = regionMapper.selectCityByProId(Integer.valueOf(proId));
        for (RegionDo regionDo : cityList) {
            cityMap.put(String.valueOf(regionDo.getId()), regionDo);
        }
        return cityMap;
    }

    /**
     * 按照<省份代码>获取城市集合
     *
     * @param proId 省份代码
     * @return 结果集
     */
    public Map<String, RegionDo> getCityByProId(String proId) {
        Map<String, RegionDo> cityMap = Maps.newConcurrentMap();
        if (allCitys.size() == NumberDict.ZERO) {
            List<RegionDo> cityList = regionMapper.selectCityByProId(Integer.valueOf(proId));
            for (RegionDo regionDo : cityList) {
                cityMap.put(String.valueOf(regionDo.getId()), regionDo);
            }
        } else {
            for (Map.Entry<String, Map<String, RegionDo>> entry : allCitys.entrySet()) {
                if(entry.getKey().equals(proId)){
                    for(Map.Entry<String, RegionDo> entry1 :entry.getValue().entrySet()){
                        cityMap.put(entry1.getKey(), entry1.getValue());
                    }
                }

            }
        }
        return cityMap;
    }
}
