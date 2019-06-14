package com.techGoal.controller;

import com.google.common.collect.Lists;
import com.techGoal.convert.CircleConvert;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.pojo.bo.CircleBo;
import com.techGoal.pojo.vo.*;
import com.techGoal.service.CircleManageService;
import com.techGoal.utils.TechGoalObjects;
import com.techGoal.utils.validation.ParamValidate;
import com.techGoal.utils.web.AjaxResult;
import com.techGoal.utils.web.WebEnhance;
import com.techGoal.utils.web.WebResultModeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description : 圈子管理-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 10:08
 */
@Slf4j
@RestController
@RequestMapping("/circle")
@Api(description = "圈子管理")
public class CircleManageController {

    /**
     * 圈子管理-服务层
     */
    @Autowired
    private CircleManageService circleManageService;

    /**
     * 根据既定条件查询匹配圈子
     *
     * @param circleReqVo 检索条件
     * @return 结果集
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-circle-by-condition")
    @ApiOperation(value = "根据既定条件查询匹配圈子")
    public AjaxResult<List<CircleVo>> getCircleByCondition(@RequestBody CircleVo circleReqVo) {
        log.info("根据既定条件查询匹配圈子,请求参数：{}", circleReqVo);
        AjaxResult<List<CircleVo>> result = new AjaxResult<>();
        List<CircleVo> circleVos = Lists.newArrayList();
        List<CircleBo> circleBos;
        if (!TechGoalObjects.isEmpty(circleReqVo.getCircleHostNo()) && !TechGoalObjects.isEmpty(circleReqVo.getCircleName())) {
            // 根据(圈名称和圈主)模糊查询我创建的圈子
            circleBos = circleManageService.getCircleByNameAndHostNo(circleReqVo.getCircleName(), circleReqVo.getCircleHostNo());
            for (CircleBo circleBo : circleBos) {
                CircleVo circleVo = CircleConvert.circleBoToConvertVo(circleBo);
                circleVos.add(circleVo);
            }
        } else if (TechGoalObjects.isEmpty(circleReqVo.getCircleHostNo()) && !TechGoalObjects.isEmpty(circleReqVo.getCircleName())) {
            // 根据(圈名称)模糊查询全平台匹配圈子
            circleBos = circleManageService.getCircleByName(circleReqVo.getCircleName());
            for (CircleBo circleBo : circleBos) {
                CircleVo circleVo = CircleConvert.circleBoToConvertVo(circleBo);
                circleVos.add(circleVo);
            }
        }
        result.setResult(circleVos);
        log.info("根据既定条件查询匹配圈子,请求参数：{}，返回结果：{}", circleReqVo, circleVos);
        return result;
    }

    /**
     * 根据圈子编号查询圈子主页信息
     *
     * @param circleReqVo 检索条件
     * @return 结果集
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-circleInfo-by-circleNo")
    @ApiOperation(value = "根据圈子编号查询圈子主页信息")
    public AjaxResult<CircleVo> getCircleInfoByCircleNo(@RequestBody CircleVo circleReqVo) {
        log.info("根据圈子编号查询圈子主页信息,请求参数：{}", circleReqVo);
        AjaxResult<CircleVo> result = new AjaxResult<>();
        CircleVo circleVo = new CircleVo();
        if (TechGoalObjects.isEmpty(circleReqVo.getCircleNo())) {
            log.error("根据圈子编号查询圈子主页信息,异常：{}", ErrorCodeEnum.ERROR_CODE_000009.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000009);
        }
        CircleBo circleBo = circleManageService.getCircleInfoByCircleNo(circleReqVo.getCircleNo());
        BeanUtils.copyProperties(circleBo, circleVo);
        result.setResult(circleVo);
        log.info("根据既定条件查询匹配圈子,请求参数：{}，返回结果：{}", circleReqVo, circleVo);
        return result;
    }

    /**
     * 根据既定条件查询<我加入>的匹配圈子(支持圈名模糊查询)
     *
     * @param circleReqVo 检索条件
     * @return 结果集
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-mycircle-by-condition")
    @ApiOperation(value = "根据既定条件查询匹配圈子")
    public AjaxResult<List<CircleVo>> getMyCircleByCondition(@RequestBody CircleVo circleReqVo) {
        log.info("根据既定条件查询我加入的匹配圈子,请求参数：{}", circleReqVo);
        if (TechGoalObjects.isEmpty(circleReqVo.getUserId())) {
            log.error("根据既定条件查询我加入的匹配圈子,异常：{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        AjaxResult<List<CircleVo>> result = new AjaxResult<>();
        List<CircleVo> circleVos = Lists.newArrayList();
        CircleBo circleBo = new CircleBo();
        BeanUtils.copyProperties(circleReqVo, circleBo);
        List<CircleBo> circleBos = circleManageService.getCircleByUserId(circleBo);
        for (CircleBo circle : circleBos) {
            CircleVo circleVo = CircleConvert.circleBoToConvertVo(circle);
            circleVos.add(circleVo);
        }
        result.setResult(circleVos);
        log.info("根据既定条件查询匹配圈子,请求参数：{}，返回结果：{}", circleReqVo, circleVos);
        return result;
    }

    /**
     * 查询圈成员信息
     *
     * @param circleReqVo 检索条件（圈号）
     * @return 结果集
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-all-circle-members")
    @ApiOperation(value = "查询圈成员信息")
    public AjaxResult<List<UserInfoVo>> getAllCircleMembers(@RequestBody CircleVo circleReqVo) {
        log.info("查询圈成员信息,请求参数：{}", circleReqVo);
        if (TechGoalObjects.isEmpty(circleReqVo.getCircleNo())) {
            log.error("查询圈成员信息,异常：{}", ErrorCodeEnum.ERROR_CODE_000009.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000009);
        }
        AjaxResult<List<UserInfoVo>> result = new AjaxResult<>();
        List<UserInfoVo> userInfoVoList = circleManageService.getAllCircleMembers(Long.valueOf(circleReqVo.getCircleNo()));
        result.setResult(userInfoVoList);
        log.info("查询圈成员信息,请求参数：{}，返回结果：{}", circleReqVo, userInfoVoList);
        return result;
    }

    /**
     * 入圈申请
     *
     * @param circleApplyVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/circle-apply")
    @ApiOperation(value = "入圈申请")
    public AjaxResult circleApply(@RequestBody CircleApplyVo circleApplyVo) {
        log.info("入圈申请,请求参数：{}", circleApplyVo);
        AjaxResult result = new AjaxResult();
        ParamValidate.validate(circleApplyVo);
        circleManageService.circleApply(circleApplyVo);
        return result;
    }

    /**
     * 圈主拉取入圈申请记录
     *
     * @param userInfoVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/circle-apply-record")
    @ApiOperation(value = "圈主拉取入圈申请记录")
    public AjaxResult<List<CircleApplyVo>> circleApplyRecord(@RequestBody UserInfoVo userInfoVo) {
        log.info("圈主拉取入圈申请记录,请求参数：{}", userInfoVo);
        AjaxResult<List<CircleApplyVo>> result = new AjaxResult<>();
        if (TechGoalObjects.isEmpty(userInfoVo.getUserId())) {
            log.error("圈主拉取入圈申请记录,异常：{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        List<CircleApplyVo> applyVoList = circleManageService.circleApplyRecord(Long.valueOf(userInfoVo.getUserId()));
        result.setResult(applyVoList);
        log.info("圈主拉取入圈申请记录,请求参数：{}，返回结果：{}", userInfoVo, applyVoList);
        return result;
    }

    /**
     * 拉取个人入圈申请记录
     *
     * @param userInfoVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/circle-apply-personal-record")
    @ApiOperation(value = "拉取个人入圈申请记录")
    public AjaxResult<List<CircleApplyVo>> circleApplyPersonalRecord(@RequestBody UserInfoVo userInfoVo) {
        log.info("拉取个人入圈申请记录,请求参数：{}", userInfoVo);
        AjaxResult<List<CircleApplyVo>> result = new AjaxResult<>();
        if (TechGoalObjects.isEmpty(userInfoVo.getUserId())) {
            log.error("拉取个人入圈申请记录,异常：{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        List<CircleApplyVo> applyVoList = circleManageService.circleApplyPerRecord(Long.valueOf(userInfoVo.getUserId()));
        log.info("拉取个人入圈申请记录,请求参数：{}，返回结果：{}", userInfoVo, applyVoList);
        result.setResult(applyVoList);
        return result;
    }

    /**
     * 圈主处理入圈申请记录
     *
     * @param handleCircleApplyVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/handle-circle-apply")
    @ApiOperation(value = "圈主处理入圈申请记录")
    public AjaxResult handleCircleApply(@RequestBody HandleCircleApplyVo handleCircleApplyVo) {
        log.info("圈主处理入圈申请记录,请求参数：{}", handleCircleApplyVo);
        ParamValidate.validate(handleCircleApplyVo);
        AjaxResult result = new AjaxResult();
        circleManageService.handleCircleApply(handleCircleApplyVo);
        return result;
    }

    /**
     * 退出圈子
     *
     * @param exitCircleVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/exit-circle")
    @ApiOperation(value = "退出圈子")
    public AjaxResult exitCircle(@RequestBody ExitCircleVo exitCircleVo) {
        log.info("退出圈子,请求参数：{}", exitCircleVo);
        ParamValidate.validate(exitCircleVo);
        AjaxResult result = new AjaxResult();
        circleManageService.exitCircle(exitCircleVo);
        return result;
    }


}
