package com.alumni.control.controller;

import com.alumni.control.pojo.dao.JobIndustryDo;
import com.alumni.control.pojo.dao.RegionDo;
import com.alumni.control.pojo.dao.UserLabelDo;
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

    @ApiOperation(value = "获取所有核心标签集合")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-labels")
    public AjaxResult<List<UserLabelDo>> getLabels() {
        AjaxResult<List<UserLabelDo>> result = new AjaxResult<>();
        log.info("获取所有核心标签集合,开始");
        List<UserLabelDo> userLabels = cacheService.getUserLabels();
        result.setResult(userLabels);
        log.info("获取所有核心标签集合,返回结果：{}", userLabels);
        return result;
    }


    @ApiOperation(value = "获取所有一级行业信息")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-levelOneIndustry")
    public AjaxResult<List<JobIndustryDo>> getLevelOneIndustry() {
        AjaxResult<List<JobIndustryDo>> result = new AjaxResult<>();
        log.info("获取所有一级行业信息,开始");
        List<JobIndustryDo> levelOneIndustry = cacheService.getLevelOneIndustry();
        result.setResult(levelOneIndustry);
        log.info("获取所有一级行业信息,返回结果：{}", levelOneIndustry);
        return result;
    }

    @ApiOperation(value = "获取所有二级行业信息")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-levelTwoIndustry/{parentId}")
    public AjaxResult<List<JobIndustryDo>> getLevelTwoIndustry(@PathVariable String parentId) {
        AjaxResult<List<JobIndustryDo>> result = new AjaxResult<>();
        log.info("获取所有二级行业信息,开始");
        List<JobIndustryDo> levelTwoIndustry = cacheService.getLevelTwoIndustry(parentId);
        result.setResult(levelTwoIndustry);
        log.info("获取所有二级行业信息,返回结果：{}", levelTwoIndustry);
        return result;
    }
}
