package com.techGoal.controller;

import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.service.UserInfoService;
import com.techGoal.utils.TechGoalObjects;
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

import java.util.List;

/**
 * description : 用户信息-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 9:08
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(description = "用户信息")
public class UserInfoController {

    /**
     * 用户个人信息-服务层
     */
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 修改用户个人信息
     *
     * @param userInfoVo 用户个人信息
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/modify-user-info")
    @ApiOperation(value = "修改用户个人信息")
    public AjaxResult modifyUserInfo(@RequestBody UserInfoVo userInfoVo) {
        AjaxResult result = new AjaxResult();
        log.info("修改用户个人信息,请求参数:{}", userInfoVo);
        ParamValidate.validate(userInfoVo);
        userInfoService.modifyUserInfo(userInfoVo);
        log.info("修改用户个人信息,成功");
        return result;
    }

    /**
     * 修改用户画像
     *
     * @param userInfoVo 用户个人信息
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/modify-user-profile")
    @ApiOperation(value = "修改用户画像")
    public AjaxResult modifyUserProfile(@RequestBody UserInfoVo userInfoVo) {
        AjaxResult result = new AjaxResult();
        log.info("修改用户画像,请求参数:{}", userInfoVo);
        if (TechGoalObjects.isEmpty(userInfoVo.getUserId())) {
            log.error("修改用户画像,异常：{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        userInfoService.modifyUserProfile(userInfoVo);
        log.info("修改用户画像,成功");
        return result;
    }

    /**
     * 通过用户名称<模糊查询>平台所有符合条件的用户
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/query-user-by-name")
    @ApiOperation(value = "查询所有符合条件的用户")
    public AjaxResult<List<UserInfoVo>> queryUserByName(@RequestBody UserInfoVo userInfoVo) {
        AjaxResult<List<UserInfoVo>> result = new AjaxResult<>();
        log.info("查询所有符合条件的用户,请求参数:{}", userInfoVo);
        List<UserInfoVo> userInfoVoList = userInfoService.queryUserByName(userInfoVo);
        result.setResult(userInfoVoList);
        log.info("查询所有符合条件的用户,返回结果：{}", userInfoVoList);
        return result;
    }

}
