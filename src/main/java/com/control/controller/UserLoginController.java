package com.techGoal.controller;

import com.techGoal.constant.RedisDict;
import com.techGoal.constant.SecurityDict;
import com.techGoal.dict.NumberLongDict;
import com.techGoal.pojo.dao.LoginInfoDo;
import com.techGoal.pojo.dto.CommonParamDto;
import com.techGoal.pojo.vo.UserLoginVo;
import com.techGoal.redis.RedisManager;
import com.techGoal.service.UserLoginService;
import com.techGoal.utils.JwtUtil;
import com.techGoal.utils.PwdEncryptUtil;
import com.techGoal.utils.SecurityUtil;
import com.techGoal.utils.validation.ParamValidate;
import com.techGoal.utils.web.AjaxResult;
import com.techGoal.utils.web.WebEnhance;
import com.techGoal.utils.web.WebResultModeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * description : 用户登录-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 22:06
 */
@Slf4j
@RestController
@RequestMapping("/")
@Api(description = "登录")
public class UserLoginController {

    /**
     * 缓存服务
     */
    @Autowired
    private RedisManager redisServiceImpl;

    /**
     * 用户注册登录-服务层
     */
    @Autowired
    private UserLoginService userLoginService;

    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public AjaxResult<UserLoginVo> login(@RequestBody UserLoginVo userLoginVo) throws Exception {
        AjaxResult<UserLoginVo> result = new AjaxResult();
        log.info("用户登录,请求参数:{}", userLoginVo);
        ParamValidate.validate(userLoginVo);
        // 校验登录信息
        String pwd = SecurityUtil.aesDecrypt(userLoginVo.getPwd(), SecurityDict.AES_LOGIN_WORD_KEY, SecurityDict.AES_LOGIN_WORD_KEY);
        userLoginVo.setPwd(PwdEncryptUtil.encrypt(pwd));
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
}
