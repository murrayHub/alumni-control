package com.techGoal.controller;

import com.google.common.collect.Lists;
import com.techGoal.convert.UserLabelConvert;
import com.techGoal.dict.NumberDict;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.pojo.dao.UserLabelDo;
import com.techGoal.pojo.vo.UserLabelVo;
import com.techGoal.pojo.vo.UserRegisterOneStepVo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.service.CacheService;
import com.techGoal.service.UserLoginService;
import com.techGoal.utils.validation.ParamValidate;
import com.techGoal.utils.web.AjaxResult;
import com.techGoal.utils.web.WebEnhance;
import com.techGoal.utils.web.WebResultModeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 缓存类
     */
    @Autowired
    private CacheService cacheService;

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

    /**
     * 获取所有用户标签集合
     * @return 结果集
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @GetMapping("/get-all-user-label")
    @ApiOperation(value = "获取所有用户标签集合")
    public AjaxResult<List<UserLabelVo>> getAllUserLabel(){
        log.info("请求后台获取所有用户标签集合");
        AjaxResult<List<UserLabelVo>> result = new AjaxResult<>();
        List<UserLabelDo> userLabels = cacheService.getUserLabels();
        List<UserLabelVo> userLabelVos = Lists.newArrayList();
        for(UserLabelDo userLabelDo : userLabels){
            UserLabelVo userLabelVo = UserLabelConvert.convertToVo(userLabelDo);
            userLabelVos.add(userLabelVo);
        }
        result.setResult(userLabelVos);
        log.info("获取所有用户标签集合,返回结果:{}", userLabelVos);
        return result;
    }
}
