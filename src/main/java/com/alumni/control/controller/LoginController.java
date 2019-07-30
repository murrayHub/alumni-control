package com.alumni.control.controller;

import com.alumni.control.constant.RedisDict;
import com.alumni.control.convert.SchoolConvert;
import com.alumni.control.dict.NumberDict;
import com.alumni.control.dict.NumberLongDict;
import com.alumni.control.enums.ErrorCodeEnum;
import com.alumni.control.enums.InvitationEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.mapper.AlumniManagerInfoDoMapper;
import com.alumni.control.mapper.SchoolMapper;
import com.alumni.control.pojo.dao.AlumniManagerInfoDo;
import com.alumni.control.pojo.dao.LoginInfoDo;
import com.alumni.control.pojo.dao.SchoolDo;
import com.alumni.control.pojo.dao.UcasInstituteDo;
import com.alumni.control.pojo.dto.CommonParamDto;
import com.alumni.control.pojo.vo.*;
import com.alumni.control.redis.RedisManager;
import com.alumni.control.service.CacheService;
import com.alumni.control.service.RegisterService;
import com.alumni.control.service.UserLoginService;
import com.alumni.control.utils.JwtUtil;
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
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private AlumniManagerInfoDoMapper alumniManagerInfoDoMapper;

    /**
     * 缓存服务
     */
    @Autowired
    private RedisManager redisServiceImpl;

    @ApiOperation(value = "评论Demo页面")
    @RequestMapping("/testComment")
    public String testComment() {
        // 参考链接： https://blog.csdn.net/solocoder/article/details/80963255
        return "/test";
    }


    @ApiOperation(value = "校友管理-注册页面")
    @RequestMapping("/registerPage")
    public String registerPage() {
        return "/register";
    }

    @ApiOperation(value = "校友管理-登录页面")
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "/login";
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
        // 检查用户账号是否已注册
        int count = registerService.checkUserLoginInfo(userRegisterVo);
        if (count > NumberDict.ZERO) {
            log.error("用户注册,异常：{}", ErrorCodeEnum.ERROR_CODE_000003.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000003);
        }
        // 数据落库
        registerService.insertUserInfo(userRegisterVo);
        return result;
    }

    @ApiOperation(value = "校友注册-提交注册信息")
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @ResponseBody
    @RequestMapping(value = "register-spec-submit")
    public AjaxResult registerSpecSubmit(@RequestBody AlumniRegisterVo alumniRegisterVo) {
        AjaxResult result = new AjaxResult();
        log.info("校友注册-提交注册信息,请求参数:{}", alumniRegisterVo);
        ParamValidate.validate(alumniRegisterVo);
        // 数据落库
        registerService.insertAlumniInfo(alumniRegisterVo);
        return result;
    }

    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录")
    public AjaxResult<CommonParamDto> login(@RequestBody UserLoginVo userLoginVo) throws Exception {
        AjaxResult<CommonParamDto> result = new AjaxResult();
        log.info("用户登录,请求参数:{}", userLoginVo);
        ParamValidate.validate(userLoginVo);
        // 校验登录信息
//        String pwd = SecurityUtil.aesDecrypt(userLoginVo.getPwd(), SecurityDict.AES_LOGIN_WORD_KEY, SecurityDict.AES_LOGIN_WORD_KEY);
//        userLoginVo.setPwd(PwdEncryptUtil.encrypt(pwd));
        LoginInfoDo loginInfoDo = userLoginService.verifyUserLoginInfo(userLoginVo);
        userLoginService.insertLoginLog(loginInfoDo);
        // 设置公共参数
        AlumniManagerInfoDo alumniManagerInfoDo = new AlumniManagerInfoDo();
        alumniManagerInfoDo.setUserId(loginInfoDo.getUserId());
        AlumniManagerInfoDo alumniManagerInfoDo1 = alumniManagerInfoDoMapper.selectOne(alumniManagerInfoDo);
        CommonParamDto commonParamDto = setCommonParams(loginInfoDo.getUserId(), alumniManagerInfoDo1.getCollegeNo());
        result.setResult(commonParamDto);
        return result;
    }

    /**
     * 设置公共参数
     *
     * @param userId      用户编号
     * @param collegeNo      学校编号
     * @return CommonParamDto
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private CommonParamDto setCommonParams(Long userId,String collegeNo) throws NoSuchAlgorithmException, InvalidKeyException {
        String key = JwtUtil.getKey();
        String token = JwtUtil.getToken(String.valueOf(userId), key);
        CommonParamDto commonParamDto = new CommonParamDto();
        commonParamDto.setCollegeNo(collegeNo);
        commonParamDto.setUserId(String.valueOf(userId));
        commonParamDto.setTokenVal(token);
        commonParamDto.setTokenKey(key);
        redisServiceImpl.insertObject(commonParamDto, RedisDict.TOKEN + (String.valueOf(userId)), NumberLongDict.TWO_HOUR);
        return commonParamDto;
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
