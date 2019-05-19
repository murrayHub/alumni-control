package com.techGoal.controller;

import com.techGoal.pojo.dao.RegionDo;
import com.techGoal.service.CacheService;
import com.techGoal.utils.web.AjaxResult;
import com.techGoal.utils.web.WebEnhance;
import com.techGoal.utils.web.WebResultModeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * description : 缓存工具服务-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/16 21:36
 */
@Slf4j
@Controller
@Api(description = "缓存工具服务-控制层")
@RequestMapping("/cache")
public class CacheController {
    /**
     * 缓存工具服务-实现类
     */
    @Autowired
    private CacheService cacheService;

    /**
     * 获取所有省份集合
     *
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @GetMapping("get-provinces")
    @ApiOperation(value = "获取所有省份集合")
    public AjaxResult<Map<String, RegionDo>> getProvinces() {
        AjaxResult<Map<String, RegionDo>> result = new AjaxResult<>();
        log.info("请求获取所有省份集合");
        Map<String, RegionDo> provinceMap = cacheService.getProvinces();
        result.setResult(provinceMap);
        log.info("获取所有省份集合：{}", provinceMap);
        return result;
    }

    /**
     * 获取城市集合
     *
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("get-citys")
    @ApiOperation(value = "获取城市集合")
    public AjaxResult<Map<String, RegionDo>> getCitys(@RequestParam String proId) {
        AjaxResult<Map<String, RegionDo>> result = new AjaxResult<>();
        log.info("获取城市集合,省份编号：{}", proId);
        Map<String, RegionDo> cityMap = cacheService.getCityByProId(proId);
        result.setResult(cityMap);
        log.info("获取城市集合：{}", cityMap);
        return result;
    }
}
