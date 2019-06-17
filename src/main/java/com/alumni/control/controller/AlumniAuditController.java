package com.alumni.control.controller;

import com.alumni.control.convert.LevelOneIdentifyConvert;
import com.alumni.control.convert.UserDegreeIdentifyConvert;
import com.alumni.control.dict.NumberDict;
import com.alumni.control.enums.ErrorCodeEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.pojo.dao.AlumniManagerInfoDo;
import com.alumni.control.pojo.dao.LevelOneIdentifyDo;
import com.alumni.control.pojo.dao.UserDegreeIdentifyDo;
import com.alumni.control.pojo.vo.*;
import com.alumni.control.service.AlumniManageService;
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

    @ApiOperation(value = "获取二级认证申请信息")
    @WebEnhance(mode = WebResultModeEnum.PAGE_QUERY)
    @ResponseBody
    @RequestMapping(value = "get-level-two-identify-info")
    public AjaxResult<PageRespDTO<UserDegreeIdentifyVo>> getLevelTwoIdentifyInfo(@RequestBody AlumniManagerInfoVo alumniManagerInfoVo) {
        AjaxResult<PageRespDTO<UserDegreeIdentifyVo>> result = new AjaxResult<>();
        log.info("获取二级认证申请信息,请求参数:{}", alumniManagerInfoVo);
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getCollegeNo())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000024);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getInstituteNo())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000027);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniManagerInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        Page<UserDegreeIdentifyDo> pageData = (Page<UserDegreeIdentifyDo>) alumniManageService.getLevelTwoIdentifyInfo(alumniManagerInfoVo);
        PageRespDTO pageResult = PageParamConvert.getPageRespDto(pageData);
        if (!CollectionUtils.isEmpty(pageData.getResult())) {
            List<UserDegreeIdentifyVo> userDegreeIdentifyVos = Lists.newArrayList();
            for (UserDegreeIdentifyDo userDegreeIdentifyDo : pageData.getResult()) {
                UserDegreeIdentifyVo userDegreeIdentifyVo = UserDegreeIdentifyConvert.toConvertVo(userDegreeIdentifyDo);
                userDegreeIdentifyVos.add(userDegreeIdentifyVo);
            }
            pageResult.setList(userDegreeIdentifyVos);
        }
        log.info("获取二级认证申请信息,返回结果:{}", pageResult);
        result.setResult(pageResult);
        return result;
    }

    @ApiOperation(value = "获取二级认证通过的校友信息-不支持修改")
    @WebEnhance(mode = WebResultModeEnum.PAGE_QUERY)
    @ResponseBody
    @RequestMapping(value = "get-level-two-identified-info")
    public AjaxResult<PageRespDTO<UserDegreeIdentifyVo>> getLevelTwoIdentifiedInfo(@RequestBody AlumniManagerInfoVo alumniManagerInfoVo) {
        AjaxResult<PageRespDTO<UserDegreeIdentifyVo>> result = new AjaxResult<>();
        log.info("获取二级认证通过的校友信息,请求参数:{}", alumniManagerInfoVo);
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getCollegeNo())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000024);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getInstituteNo())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000027);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniManagerInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        Page<UserDegreeIdentifyDo> pageData = (Page<UserDegreeIdentifyDo>) alumniManageService.getLevelTwoIdentifiedInfo(alumniManagerInfoVo);
        PageRespDTO pageResult = PageParamConvert.getPageRespDto(pageData);
        if (!CollectionUtils.isEmpty(pageData.getResult())) {
            pageResult.setList(alumniManageService.dealWithViewResults(pageData.getResult()));
        }
        log.info("获取二级认证通过的校友信息,返回结果:{}", pageResult);
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
        return result;
    }

    @ApiOperation(value = "获取一级认证申请信息")
    @WebEnhance(mode = WebResultModeEnum.PAGE_QUERY)
    @ResponseBody
    @RequestMapping(value = "get-level-one-identify-info")
    public AjaxResult<PageRespDTO<UserDegreeIdentifyVo>> getLevelOneIdentifyInfo(@RequestBody AlumniManagerInfoVo alumniManagerInfoVo) {
        AjaxResult<PageRespDTO<UserDegreeIdentifyVo>> result = new AjaxResult<>();
        log.info("获取一级认证申请信息,请求参数:{}", alumniManagerInfoVo);
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getCollegeNo())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000024);
        }
        if (TechGoalObjects.isEmpty(alumniManagerInfoVo.getManagerId())) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000028);
        }
        // 鉴定操作员身份权限
        AlumniManagerInfoDo alumniManagerInfoDo = alumniManageService.getManagerInfo(Long.valueOf(alumniManagerInfoVo.getManagerId()));
        if (TechGoalObjects.isEmpty(alumniManagerInfoDo)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000029);
        }
        if (alumniManagerInfoDo.getManagerLevel() != NumberDict.ONE) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000034);
        }
        Page<LevelOneIdentifyDo> pageData = (Page<LevelOneIdentifyDo>) alumniManageService.getLevelOneIdentifyInfo(alumniManagerInfoVo);
        PageRespDTO pageResult = PageParamConvert.getPageRespDto(pageData);
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

    @ApiOperation(value = "一级认证初审处理")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "level-one-identify-update")
    public AjaxResult levelOneIdentifyUpdate(@RequestBody LevelOneAlumniUpdInfoVo levelOneAlumniUpdInfoVo) {
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
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000030);
        }
        if (alumniManagerInfoDo.getManagerType() != NumberDict.TWO) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000031);
        }
        try {
            alumniManageService.updateLevelOneAlumniInfo(levelOneAlumniUpdInfoVo);
        } catch (Exception e) {
            throw new BizServiceException(WebExceptionUtils.getWebErrorCode(e));
        }
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
        return result;
    }
}
