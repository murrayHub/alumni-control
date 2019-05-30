package com.techGoal.controller;

import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.pojo.vo.MomentsVo;
import com.techGoal.service.MomentsManageService;
import com.techGoal.utils.TechGoalObjects;
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
 * description : 朋友圈动态管理-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/30 10:42
 */
@Slf4j
@RestController
@RequestMapping("/moments")
@Api(description = "朋友圈动态管理")
public class MomentsManageController {

    /**
     * 朋友圈动态管理-服务层
     */
    @Autowired
    private MomentsManageService momentsManageService;

    /**
     * 个人-朋友圈动态-新增
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/create-moments")
    @ApiOperation(value = "个人-朋友圈动态-新增")
    public AjaxResult createMoments(@RequestBody MomentsVo momentsVo){
        log.info("个人朋友圈动态-新增,请求参数：{}", momentsVo);
        AjaxResult result = new AjaxResult();
        if (TechGoalObjects.isEmpty(momentsVo.getPublisherId())) {
            log.error("个人朋友圈动态-新增,异常：{}", ErrorCodeEnum.ERROR_CODE_000014.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000014);
        }
        if (TechGoalObjects.isEmpty(momentsVo.getContent())) {
            log.error("个人朋友圈动态-新增,异常：{}", ErrorCodeEnum.ERROR_CODE_000015.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000015);
        }
        momentsManageService.createPerMoments(momentsVo);
        log.info("个人朋友圈动态-新增,成功！ 请求参数：{}", momentsVo);
        return result;
    }

    /**
     * 圈子-朋友圈动态-新增
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/create-circle-moments")
    @ApiOperation(value = "圈子-朋友圈动态-新增")
    public AjaxResult createCircleMoments(@RequestBody MomentsVo momentsVo){
        log.info("圈子-朋友圈动态-新增,请求参数：{}", momentsVo);
        AjaxResult result = new AjaxResult();
        if (TechGoalObjects.isEmpty(momentsVo.getPublisherId())) {
            log.error("圈子-朋友圈动态-新增,异常：{}", ErrorCodeEnum.ERROR_CODE_000014.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000014);
        }
        if (TechGoalObjects.isEmpty(momentsVo.getCircleNo())) {
            log.error("圈子-朋友圈动态-新增,异常：{}", ErrorCodeEnum.ERROR_CODE_000009.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000009);
        }
        if (TechGoalObjects.isEmpty(momentsVo.getContent())) {
            log.error("圈子-朋友圈动态-新增,异常：{}", ErrorCodeEnum.ERROR_CODE_000015.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000015);
        }
        momentsManageService.createCircleMoments(momentsVo);
        log.info("圈子-朋友圈动态-新增,成功！ 请求参数：{}", momentsVo);
        return result;
    }

    /**
     * 仅看自己-朋友圈动态-查询
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-personal-moments")
    @ApiOperation(value = "个人朋友圈动态-查询")
    public AjaxResult getPersonalMoments(@RequestBody MomentsVo momentsVo){
        AjaxResult result = new AjaxResult();
        return result;
    }
    /**
     * 发现-朋友圈动态-查询
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-discover-moments")
    @ApiOperation(value = "个人朋友圈动态-查询")
    public AjaxResult getDiscoverMoments(@RequestBody MomentsVo momentsVo){
        AjaxResult result = new AjaxResult();
        return result;
    }
}
