package com.alumni.control.controller;

import com.alumni.control.constant.RedisDict;
import com.alumni.control.convert.SchoolConvert;
import com.alumni.control.dict.NumberDict;
import com.alumni.control.dict.NumberLongDict;
import com.alumni.control.enums.ErrorCodeEnum;
import com.alumni.control.enums.InvitationEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.mapper.SchoolMapper;
import com.alumni.control.pojo.dao.LoginInfoDo;
import com.alumni.control.pojo.dao.SchoolDo;
import com.alumni.control.pojo.dao.UcasInstituteDo;
import com.alumni.control.pojo.dto.CommonParamDto;
import com.alumni.control.pojo.vo.SchoolVo;
import com.alumni.control.pojo.vo.UcasInstituteVo;
import com.alumni.control.pojo.vo.UserLoginVo;
import com.alumni.control.pojo.vo.UserRegisterVo;
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

    /**
     * 缓存服务
     */
    @Autowired
    private RedisManager redisServiceImpl;

    @ApiOperation(value = "校友管理-注册页面")
    @GetMapping("/registerPage")
    public String registerPage() {
        return "/register";
    }

    @ApiOperation(value = "校友管理-登录页面")
    @GetMapping("/loginPage")
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

    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public AjaxResult<UserLoginVo> login(@RequestBody UserLoginVo userLoginVo) throws Exception {
        AjaxResult<UserLoginVo> result = new AjaxResult();
        log.info("用户登录,请求参数:{}", userLoginVo);
        ParamValidate.validate(userLoginVo);
        // 校验登录信息
//        String pwd = SecurityUtil.aesDecrypt(userLoginVo.getPwd(), SecurityDict.AES_LOGIN_WORD_KEY, SecurityDict.AES_LOGIN_WORD_KEY);
//        userLoginVo.setPwd(PwdEncryptUtil.encrypt(pwd));
        LoginInfoDo loginInfoDo = userLoginService.verifyUserLoginInfo(userLoginVo);
        userLoginService.insertLoginLog(loginInfoDo);
        // 设置公共参数
        String token = setCommonParams(userLoginVo, loginInfoDo.getUserId());
        UserLoginVo userLoginRespVo = new UserLoginVo();
        userLoginRespVo.setToken(token);
        result.setResult(userLoginRespVo);
        return result;
    }

    /**
     * 设置公共参数
     *
     * @param userLoginVo 用户登录信息
     * @param userId      用户编号
     * @return token
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private String setCommonParams(UserLoginVo userLoginVo, Long userId) throws NoSuchAlgorithmException, InvalidKeyException {
        String key = JwtUtil.getKey();
        String token = JwtUtil.getToken(String.valueOf(userId), key);
        CommonParamDto commonParamDto = new CommonParamDto();
        commonParamDto.setLoginNo(userLoginVo.getLoginNo());
        commonParamDto.setUserId(String.valueOf(userId));
        commonParamDto.setTokenKey(key);
        redisServiceImpl.insertObject(commonParamDto, RedisDict.TOKEN + (String.valueOf(userId)), NumberLongDict.TWO_HOUR_SECOND);
        return token;
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
