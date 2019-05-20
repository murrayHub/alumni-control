package com.techGoal.controller;

import com.techGoal.dict.NumberDict;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.pojo.vo.UserRegisterOneStepVo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.service.UserLoginService;
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

/**
 * description : 用户注册-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 20:42
 */
@Slf4j
@RestController
@RequestMapping("/")
@Api(description = "注册")
public class RegisterController {

    /**
     * 用户注册登录
     */
    @Autowired
    private UserLoginService userLoginService;

    /**
     * 用户注册第一步
     *
     * @param userRegisterOneStepVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/register-one-step")
    @ApiOperation(value = "用户注册第一步")
    public AjaxResult registerOneStep(@RequestBody UserRegisterOneStepVo userRegisterOneStepVo) {
        AjaxResult result = new AjaxResult();
        log.info("用户注册第一步,请求参数:{}", userRegisterOneStepVo);
        ParamValidate.validate(userRegisterOneStepVo);
        // 检查用户账号是否已注册
        int count = userLoginService.checkUserLoginInfo(userRegisterOneStepVo);
        if (count > NumberDict.ZERO) {
            log.error("用户注册第一步,异常：{}", ErrorCodeEnum.ERROR_CODE_000003.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000003);
        }
        userLoginService.insertUserLoginInfo(userRegisterOneStepVo);
        log.info("用户注册第一步,完成");
        return result;
    }

    /**
     * 用户注册第二步
     *
     * @param userInfoVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/register-two-step")
    @ApiOperation(value = "用户注册第二步")
    public AjaxResult registerTwoStep(@RequestBody UserInfoVo userInfoVo) {
        AjaxResult result = new AjaxResult();
        log.info("用户注册第二步,请求参数:{}", userInfoVo);
        ParamValidate.validate(userInfoVo);
        userLoginService.insertUserInfo(userInfoVo);
        log.info("用户注册第二步,完成");
        return result;
    }
}
