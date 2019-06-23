package com.alumni.control.controller;

import com.alumni.control.convert.SchoolConvert;
import com.alumni.control.enums.ErrorCodeEnum;
import com.alumni.control.enums.InvitationEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.mapper.SchoolMapper;
import com.alumni.control.pojo.dao.SchoolDo;
import com.alumni.control.pojo.dao.UcasInstituteDo;
import com.alumni.control.pojo.vo.SchoolVo;
import com.alumni.control.pojo.vo.UcasInstituteVo;
import com.alumni.control.pojo.vo.UserRegisterVo;
import com.alumni.control.service.CacheService;
import com.alumni.control.utils.TechGoalObjects;
import com.alumni.control.utils.validation.ParamValidate;
import com.alumni.control.utils.web.AjaxResult;
import com.alumni.control.utils.web.WebEnhance;
import com.alumni.control.utils.web.WebResultModeEnum;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * description : 登录-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/23 10:43
 */
@Api(description = "login-Controller")
@Slf4j
@Controller
@RequestMapping("base")
public class LoginController {

    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private CacheService cacheService;

    @ApiOperation(value = "校友管理-注册页面")
    @GetMapping("/register")
    public String alumniManageDetail() {
        return "/register";
    }

    @ApiOperation(value = "提交注册信息")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "register-submit")
    public AjaxResult registerSubmit(@RequestBody UserRegisterVo userRegisterVo) {
        AjaxResult result = new AjaxResult();
        log.info("提交注册信息,请求参数:{}", userRegisterVo);
        ParamValidate.validate(userRegisterVo);
        String invitationReq = userRegisterVo.getInvitation();
        if (TechGoalObjects.isEmpty(InvitationEnum.getDescByCode(invitationReq))) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000037);
        }
        if ("UCAS03".equals(invitationReq) || "UCAS04".equals(invitationReq)) {
            if (TechGoalObjects.isEmpty(userRegisterVo.getInstitute())) {
                throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000038);
            }
        }
        // 数据落库

        return result;
    }

    @ApiOperation(value = "获取学校集合")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-colleges")
    public AjaxResult<List<SchoolVo>> getColleges() {
        AjaxResult<List<SchoolVo>> result = new AjaxResult();
        List<SchoolDo> schoolDos = schoolMapper.getSchools();
        List<SchoolVo> schoolVos = Lists.newArrayList();
        for (SchoolDo schoolDo : schoolDos) {
            SchoolVo schoolVo = SchoolConvert.toConvert(schoolDo);
            schoolVos.add(schoolVo);
        }
        result.setResult(schoolVos);
        log.info("获取学校集合,返回结果:{}", schoolVos);
        return result;
    }

    @ApiOperation(value = "获取学院集合")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "get-institute")
    public AjaxResult<List<UcasInstituteVo>> getInstitute() {
        AjaxResult<List<UcasInstituteVo>> result = new AjaxResult();
        List<UcasInstituteDo> list = cacheService.getInstitutes();
        List<UcasInstituteVo> ucasInstituteVos = Lists.newArrayList();
        for (UcasInstituteDo ucasInstituteDo : list) {
            UcasInstituteVo ucasInstituteVo1 = SchoolConvert.toConvert(ucasInstituteDo);
            ucasInstituteVos.add(ucasInstituteVo1);
        }
        result.setResult(ucasInstituteVos);
        log.info("获取学院集合,返回结果:{}", ucasInstituteVos);
        return result;
    }
}
