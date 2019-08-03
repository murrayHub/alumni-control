package com.alumni.control.controller;

import com.alumni.control.convert.LevelOneIdentifyConvert;
import com.alumni.control.dict.NumberDict;
import com.alumni.control.enums.ErrorCodeEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.mapper.UserDegreeIdentifyDoMapper;
import com.alumni.control.pojo.dao.AlumniManagerInfoDo;
import com.alumni.control.pojo.dao.LevelOneIdentifyDo;
import com.alumni.control.pojo.dao.UserDegreeIdentifyDo;
import com.alumni.control.pojo.vo.*;
import com.alumni.control.service.AlumniManageService;
import com.alumni.control.service.CacheService;
import com.alumni.control.utils.TechGoalObjects;
import com.alumni.control.utils.page.PageParamConvert;
import com.alumni.control.utils.page.PageRespDTO;
import com.alumni.control.utils.validation.ParamValidate;
import com.alumni.control.utils.web.AjaxResult;
import com.alumni.control.utils.web.WebEnhance;
import com.alumni.control.utils.web.WebExceptionUtils;
import com.alumni.control.utils.web.WebResultModeEnum;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * description : 认证审核-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 10:32
 */
@Api(description = "Test-Controller")
@Slf4j
@Controller
@RequestMapping("audit")
public class AlumniAuditController {

    /**
     * 校友信息管理-服务层
     */
    @Autowired
    private AlumniManageService alumniManageService;

    @Autowired
    private UserDegreeIdentifyDoMapper userDegreeIdentifyDoMapper;
    @Autowired
    private CacheService cacheService;

    @ApiOperation(value = "校友管理页面")
    @GetMapping("/alumni-manage")
    public String alumniManage() {
        return "/alumniInfoList";
    }

    @ApiOperation(value = "校友管理页面-详情页面")
    @GetMapping("/alumni-manage-detail")
    public String alumniManageDetail() {
        return "/alumniInfoDetail";
    }

    @ApiOperation(value = "校友一级管理页面")
    @GetMapping("/alumni-manage-one-level")
    public String alumniManageOneLevel() {
        return "/alumniInfoOneLevelList";
    }

    @ApiOperation(value = "校友一级管理页面-详情页面")
    @GetMapping("/alumni-manage-one-level-detail")
    public String alumniManageOneLevelDetail() {
        return "/alumniInfoOneLevelDetail";
    }

    @ApiOperation(value = "校友注册-专用通道")
    @GetMapping("/alumni-register-special")
    public String alumniRegisterSpecial() {
        return "/alumniRegisterSpecial";
    }

    @ApiOperation(value = "校友注册-专用通道")
    @GetMapping("/testSex")
    public String testSex() {
        return "/testSex";
    }

    @ApiOperation(value = "获取校友详情信息")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-alumni-info")
    public AjaxResult<UserDegreeIdentifyVo> getAlumniInfo(@RequestBody AlumniManagerInfoVo alumniManagerInfoVo) {
        AjaxResult<UserDegreeIdentifyVo> result = new AjaxResult<>();
        log.info("获取校友详情信息,请求参数:{}", alumniManagerInfoVo);
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getIdentifyCollegeId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000035);
        }
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniManagerInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        alumniManagerInfoVo.setCollegeNo(alumniManagerInfoDo.getCollegeNo());
        List<UserDegreeIdentifyDo> resultList = alumniManageService.getAlumniInfo(alumniManagerInfoVo);
        if (!CollectionUtils.isEmpty(resultList)) {
            result.setResult(alumniManageService.dealWithViewResults(resultList).get(NumberDict.ZERO));
        }
        result.getResult().setUcasInstituteDoList(cacheService.getInstitutes());
        log.info("获取校友详情信息,返回结果:{}", result);
        return result;
    }

    @ApiOperation(value = "获取校友信息-分页展示")
    @WebEnhance(mode = WebResultModeEnum.PAGE_QUERY)
    @ResponseBody
    @RequestMapping(value = "get-alumni-infos")
    public AjaxResult<PageRespDTO<UserDegreeIdentifyVo>> getAlumniInfos(@RequestBody AlumniManagerInfoVo alumniManagerInfoVo) {
        AjaxResult<PageRespDTO<UserDegreeIdentifyVo>> result = new AjaxResult<>();
        log.info("获取二级认证通过的校友信息,请求参数:{}", alumniManagerInfoVo);
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getCollegeNo())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000024);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        UserDegreeIdentifyDo userDegreeIdentifyDo = new UserDegreeIdentifyDo();
        userDegreeIdentifyDo.setCollegeNo(alumniManagerInfoVo.getCollegeNo());
        if (TechGoalObjects.isNotEmpty(alumniManagerInfoVo.getGenderType())) {
            if (NumberDict.MINUS_ONE != (Integer.valueOf(alumniManagerInfoVo.getGenderType()))) {
                userDegreeIdentifyDo.setGender(Integer.valueOf(alumniManagerInfoVo.getGenderType()));
            }
        }
        if (TechGoalObjects.isNotEmpty(alumniManagerInfoVo.getInstituteNo())) {
            userDegreeIdentifyDo.setInstituteNo(Long.valueOf(alumniManagerInfoVo.getInstituteNo()));
        }
        if (TechGoalObjects.isNotEmpty(alumniManagerInfoVo.getDegreeType())) {
            if (NumberDict.MINUS_ONE != (Integer.valueOf(alumniManagerInfoVo.getDegreeType()))) {
                userDegreeIdentifyDo.setDegreeNo(Integer.valueOf(alumniManagerInfoVo.getDegreeType()));
            }
        }
        if (TechGoalObjects.isNotEmpty(alumniManagerInfoVo.getIdentifyType())) {
            if (NumberDict.MINUS_ONE != (Integer.valueOf(alumniManagerInfoVo.getIdentifyType()))) {
                userDegreeIdentifyDo.setIdentifyType(Integer.valueOf(alumniManagerInfoVo.getIdentifyType()));
            }
        }
        if (TechGoalObjects.isNotEmpty(alumniManagerInfoVo.getIdentifyStatus())) {
            if (NumberDict.MINUS_ONE != (Integer.valueOf(alumniManagerInfoVo.getIdentifyStatus()))) {
                userDegreeIdentifyDo.setIdentifyStatus(Integer.valueOf(alumniManagerInfoVo.getIdentifyStatus()));
            }
        }
        if (TechGoalObjects.isNotEmpty(alumniManagerInfoVo.getIdentifyCollegeId())) {
            userDegreeIdentifyDo.setIdentifyCollegeId(Long.valueOf(alumniManagerInfoVo.getIdentifyCollegeId()));
        }
        userDegreeIdentifyDo.setStudentName(alumniManagerInfoVo.getStudentName());
        userDegreeIdentifyDo.setGrade(alumniManagerInfoVo.getGrade());
        Page<UserDegreeIdentifyDo> pageData = (Page<UserDegreeIdentifyDo>) userDegreeIdentifyDoMapper.getAlumniInfos(userDegreeIdentifyDo);
        PageRespDTO pageResult = PageParamConvert.getPageRespDto(pageData);
        if (!CollectionUtils.isEmpty(pageData.getResult())) {
            pageResult.setList(alumniManageService.dealWithViewResults(pageData.getResult()));
        }
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniManagerInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        log.info("获取校友信息,返回结果:{}", pageResult);
        result.setResult(pageResult);
        return result;
    }

    @ApiOperation(value = "二级认证初审处理")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "level-two-identify-update")
    public AjaxResult levelTwoIdentifyUpdate(@RequestBody AlumniInfoUpdVo alumniInfoUpdVo) {
        AjaxResult result = new AjaxResult();
        log.info("二级认证初审处理,请求参数:{}", alumniInfoUpdVo);
        ParamValidate.validate(alumniInfoUpdVo);
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniInfoUpdVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        if (alumniManagerInfoDo.getManagerLevel() != NumberDict.TWO) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000030);
        }
        if (alumniManagerInfoDo.getManagerType() != NumberDict.TWO) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000031);
        }
        try {
            alumniManageService.updateAlumniInfo(alumniInfoUpdVo);
            log.info("二级认证初审处理,成功");
        } catch (Exception e) {
            throw new BizServiceException(WebExceptionUtils.getWebErrorCode(e));
        }
        return result;
    }

    @ApiOperation(value = "二级认证复审处理")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "level-two-update-audit")
    public AjaxResult levelTwoUpdateAudit(@RequestBody AlumniInfoUpdVo alumniInfoUpdVo) {
        AjaxResult result = new AjaxResult();
        log.info("二级认证复审处理,请求参数:{}", alumniInfoUpdVo);
        ParamValidate.validate(alumniInfoUpdVo);
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniInfoUpdVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        if (alumniManagerInfoDo.getManagerLevel() != NumberDict.TWO) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000032);
        }
        if (alumniManagerInfoDo.getManagerType() != NumberDict.ONE) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000033);
        }
        alumniManageService.levelTwoUpdateAudit(alumniInfoUpdVo);
        log.info("二级认证复审处理,成功");
        return result;
    }

    @ApiOperation(value = "获取一级认证申请信息-分页")
    @WebEnhance(mode = WebResultModeEnum.PAGE_QUERY)
    @ResponseBody
    @RequestMapping(value = "get-level-one-identify-info")
    public AjaxResult<PageRespDTO<LevelOneIdentifyVo>> getLevelOneIdentifyInfo(@RequestBody AlumniManagerInfoVo alumniManagerInfoVo) {
        AjaxResult<PageRespDTO<LevelOneIdentifyVo>> result = new AjaxResult<>();
        log.info("获取一级认证申请信息,请求参数:{}", alumniManagerInfoVo);
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getCollegeNo())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000024);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        Page<LevelOneIdentifyDo> pageData = (Page<LevelOneIdentifyDo>) alumniManageService.getLevelOneIdentifyInfo(alumniManagerInfoVo);
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniManagerInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        if (alumniManagerInfoDo.getManagerLevel() != NumberDict.ONE) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000034);
        }
        PageRespDTO pageResult = PageParamConvert.getPageRespDto(pageData);
        // DO -> VO
        if (!CollectionUtils.isEmpty(pageData.getResult())) {
            List<LevelOneIdentifyVo> levelOneIdentifyVos = Lists.newArrayList();
            for (LevelOneIdentifyDo levelOneIdentifyDo : pageData.getResult()) {
                LevelOneIdentifyVo levelOneIdentifyVo = LevelOneIdentifyConvert.toConvertVo(levelOneIdentifyDo);
                levelOneIdentifyVos.add(levelOneIdentifyVo);
            }
            pageResult.setList(levelOneIdentifyVos);
        }
        log.info("获取一级认证申请信息,返回结果:{}", pageResult);
        result.setResult(pageResult);
        return result;
    }

    @ApiOperation(value = "获取一级认证申请信息")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-level-one-identify-info-detail")
    public AjaxResult<LevelOneIdentifyVo> getLevelOneIdentifyInfoDetail(@RequestBody AlumniManagerInfoVo alumniManagerInfoVo) {
        AjaxResult<LevelOneIdentifyVo> result = new AjaxResult<>();
        log.info("获取一级认证申请信息,请求参数:{}", alumniManagerInfoVo);
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getCollegeNo())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000024);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getIdentifyCollegeId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000035);
        }
        List<LevelOneIdentifyDo> levelOneIdentifyDos = alumniManageService.getLevelOneIdentifyInfoDetail(alumniManagerInfoVo);
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniManagerInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        if (alumniManagerInfoDo.getManagerLevel() != NumberDict.ONE) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000034);
        }
        // DO -> VO
        List<LevelOneIdentifyVo> levelOneIdentifyVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(levelOneIdentifyDos)) {
            for (LevelOneIdentifyDo levelOneIdentifyDo : levelOneIdentifyDos) {
                LevelOneIdentifyVo levelOneIdentifyVo = LevelOneIdentifyConvert.toConvertVo(levelOneIdentifyDo);
                levelOneIdentifyVos.add(levelOneIdentifyVo);
            }
        }
        result.setResult(levelOneIdentifyVos.get(NumberDict.ZERO));
        log.info("获取一级认证申请信息,返回结果:{}", result);
        return result;
    }

    @ApiOperation(value = "一级认证校友信息修改")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "level-one-identify-update")
    public AjaxResult levelOneIdentifyUpdate(@RequestBody LevelOneAlumniUpdInfoVo levelOneAlumniUpdInfoVo) {
        AjaxResult result = new AjaxResult();
        log.info("一级认证校友信息修改,请求参数:{}", levelOneAlumniUpdInfoVo);
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getIdentifyCollegeId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000035);
        }
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getIdentifyStatus())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000036);
        }
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(levelOneAlumniUpdInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        if (alumniManagerInfoDo.getManagerLevel() != NumberDict.ONE) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000030);
        }
        if (alumniManagerInfoDo.getManagerType() != NumberDict.TWO) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000031);
        }
        try {
            alumniManageService.updateLevelOneAlumniInfo(levelOneAlumniUpdInfoVo);
            log.info("一级认证校友信息修改,成功");
        } catch (Exception e) {
            throw new BizServiceException(WebExceptionUtils.getWebErrorCode(e));
        }
        return result;
    }


    @ApiOperation(value = "一级认证初审处理")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "level-one-update-first-audit")
    public AjaxResult levelOneUpdateFirstAudit(@RequestBody LevelOneAlumniUpdInfoVo levelOneAlumniUpdInfoVo) {
        AjaxResult result = new AjaxResult();
        log.info("一级认证初审处理,请求参数:{}", levelOneAlumniUpdInfoVo);
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getIdentifyCollegeId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000035);
        }
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getIdentifyStatus())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000036);
        }
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(levelOneAlumniUpdInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        if (alumniManagerInfoDo.getManagerLevel() != NumberDict.ONE) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000032);
        }
        if (alumniManagerInfoDo.getManagerType() != NumberDict.TWO) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000033);
        }
        alumniManageService.levelOneUpdateAudit(levelOneAlumniUpdInfoVo);
        log.info("一级认证复审处理,成功");
        return result;
    }

    @ApiOperation(value = "一级认证复审处理")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "level-one-update-audit")
    public AjaxResult levelOneUpdateAudit(@RequestBody LevelOneAlumniUpdInfoVo levelOneAlumniUpdInfoVo) {
        AjaxResult result = new AjaxResult();
        log.info("一级认证复审处理,请求参数:{}", levelOneAlumniUpdInfoVo);
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getIdentifyCollegeId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000035);
        }
        if (TechGoalObjects.isEmpty(levelOneAlumniUpdInfoVo.getIdentifyStatus())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000036);
        }
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(levelOneAlumniUpdInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        if (alumniManagerInfoDo.getManagerLevel() != NumberDict.ONE) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000032);
        }
        if (alumniManagerInfoDo.getManagerType() != NumberDict.ONE) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000033);
        }
        alumniManageService.levelOneUpdateAudit(levelOneAlumniUpdInfoVo);
        log.info("一级认证复审处理,成功");
        return result;
    }
}
