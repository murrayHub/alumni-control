package com.techGoal.controller;

import com.techGoal.dict.NumberStrDict;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.pojo.vo.AttentionRelationVo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.service.AttentionRelationService;
import com.techGoal.utils.TechGoalObjects;
import com.techGoal.utils.validation.ParamValidate;
import com.techGoal.utils.web.AjaxResult;
import com.techGoal.utils.web.WebEnhance;
import com.techGoal.utils.web.WebResultModeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * description : 关注和被关注者关系管理-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 22:14
 */
@Slf4j
@Controller
@Api(description = "关注和被关注者关系管理-控制层")
@RequestMapping("/attention")
public class AttentionRelationController {

    /**
     * 关注和被关注者关系管理-服务层
     */
    @Autowired
    private AttentionRelationService attentionRelationService;

    /**
     * 加关注/取消关注
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/pay-attention")
    @ApiOperation(value = "加关注/取消关注")
    public AjaxResult payAttention(@RequestBody AttentionRelationVo attentionRelationVo) {
        AjaxResult result = new AjaxResult();
        log.info("加关注,请求参数:{}", attentionRelationVo);
        ParamValidate.validate(attentionRelationVo);
        if (attentionRelationVo.getOperateType().equals(NumberStrDict.ONE)) {
            attentionRelationService.payAttention(attentionRelationVo);
            log.info("加关注,成功，请求参数:{}", attentionRelationVo);
        } else {
            attentionRelationService.cancelPayAttention(attentionRelationVo);
            log.info("取消关注,成功，请求参数:{}", attentionRelationVo);
        }
        return result;
    }

    /**
     * 通过<用户编号>+<用户名称(可选，支持模糊查询)>获取所有我关注的好友
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-pay-attention-users")
    @ApiOperation(value = "获取所有我关注的用户")
    public AjaxResult<List<UserInfoVo>> getPayAttentionUsers(@RequestBody AttentionRelationVo attentionRelationVo) {
        AjaxResult<List<UserInfoVo>> result = new AjaxResult<>();
        log.info("获取所有我关注的用户,请求参数:{}", attentionRelationVo);
        if (TechGoalObjects.isEmpty(attentionRelationVo.getUserId())) {
            log.error("获取所有我关注的用户，异常:{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        List<UserInfoVo> userInfoVoList = attentionRelationService.getPayAttentionUsers(attentionRelationVo);
        if (!CollectionUtils.isEmpty(userInfoVoList)) {
            result.setResult(userInfoVoList);
        }
        log.info("获取所有我关注的用户,请求参数：{},返回结果：{}", attentionRelationVo, userInfoVoList);
        return result;
    }

    /**
     * 通过<用户编号>+<用户名称(可选，支持模糊查询)>获取所有关注我的用户
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-bePayed-attention-users")
    @ApiOperation(value = "获取所有关注我的用户")
    public AjaxResult<List<UserInfoVo>> getBePayedAttentionUsers(@RequestBody AttentionRelationVo attentionRelationVo) {
        AjaxResult<List<UserInfoVo>> result = new AjaxResult<>();
        log.info("获取所有关注我的用户,请求参数:{}", attentionRelationVo);
        if (TechGoalObjects.isEmpty(attentionRelationVo.getUserId())) {
            log.error("获取所有关注我的用户，异常:{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        List<UserInfoVo> userInfoVoList = attentionRelationService.getBePayedAttentionUsers(attentionRelationVo);
        if (!CollectionUtils.isEmpty(userInfoVoList)) {
            result.setResult(userInfoVoList);
        }
        log.info("获取所有关注我的用户,请求参数：{},返回结果：{}", attentionRelationVo, userInfoVoList);
        return result;
    }

    /**
     * 通过<用户编号>获取所有尚未关注的用户
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-noPay-attention-users")
    @ApiOperation(value = "获取所有尚未关注的用户")
    public AjaxResult<List<UserInfoVo>> getNoPayAttentionUsers(@RequestBody AttentionRelationVo attentionRelationVo) {
        AjaxResult<List<UserInfoVo>> result = new AjaxResult<>();
        log.info("获取所有尚未关注的用户,请求参数:{}", attentionRelationVo);
        if (TechGoalObjects.isEmpty(attentionRelationVo.getUserId())) {
            log.error("获取所有尚未关注的用户，异常:{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        List<UserInfoVo> userInfoVoList = attentionRelationService.getNoPayAttentionUsers(attentionRelationVo);
        if (!CollectionUtils.isEmpty(userInfoVoList)) {
            result.setResult(userInfoVoList);
        }
        log.info("获取所有尚未关注的用户,请求参数：{},返回结果：{}", attentionRelationVo, userInfoVoList);
        return result;
    }
}