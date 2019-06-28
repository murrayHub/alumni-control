package com.alumni.control.controller;

import com.alumni.control.pojo.dao.RegionDo;
import com.alumni.control.service.CacheService;
import com.alumni.control.utils.web.AjaxResult;
import com.alumni.control.utils.web.WebEnhance;
import com.alumni.control.utils.web.WebResultModeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * description : 缓存数据-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/28 14:55
 */
@Api(description = "cache-Controller")
@Slf4j
@Controller
@RequestMapping("cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @ApiOperation(value = "获取所有省份集合")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-provinces")
    public AjaxResult<List<RegionDo>> getProvinces() {
        log.info("获取所有省份集合,开始");
        AjaxResult<List<RegionDo>> result = new AjaxResult<>();
        List<RegionDo> regionDos = cacheService.getProvinceList();
        result.setResult(regionDos);
        log.info("获取所有省份集合,返回结果：{}", regionDos);
        return result;
    }

    @ApiOperation(value = "获取所有地级市集合")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-cities/{proId}")
    public AjaxResult<List<RegionDo>> getCities(@PathVariable String proId) {
        log.info("获取所有地级市集合,开始");
        AjaxResult<List<RegionDo>> result = new AjaxResult<>();
        List<RegionDo> regionDos = cacheService.getCityList(proId);
        result.setResult(regionDos);
        log.info("获取所有地级市集合,返回结果：{}", regionDos);
        return result;
    }
}
