package com.techGoal.controller;

import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.pojo.dto.MomentsDetailDto;
import com.techGoal.pojo.dto.PersonalMomentsDto;
import com.techGoal.pojo.vo.CirclePraiseVo;
import com.techGoal.pojo.vo.CommentVo;
import com.techGoal.pojo.vo.MomentsVo;
import com.techGoal.service.MomentsManageService;
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
     *
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/create-moments")
    @ApiOperation(value = "个人-朋友圈动态-新增")
    public AjaxResult createMoments(@RequestBody MomentsVo momentsVo) {
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
     *
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/create-circle-moments")
    @ApiOperation(value = "圈子-朋友圈动态-新增")
    public AjaxResult createCircleMoments(@RequestBody MomentsVo momentsVo) {
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
     * 仅看自己-朋友圈动态-查询(分页未做！)
     *
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-personal-moments")
    @ApiOperation(value = "个人朋友圈动态-查询")
    public AjaxResult<List<PersonalMomentsDto>> getPersonalMoments(@RequestBody MomentsVo momentsVo) {
        log.info("个人朋友圈动态-查询,请求参数：{}", momentsVo);
        AjaxResult<List<PersonalMomentsDto>> result = new AjaxResult();
        List<PersonalMomentsDto> personalMomentsDtos;
        // 动态内容获取
        if (TechGoalObjects.isEmpty(momentsVo.getUserId())) {
            log.error("个人朋友圈动态-查询,异常：{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        personalMomentsDtos = momentsManageService.getPersonalMoments(Long.valueOf(momentsVo.getUserId()));
        result.setResult(personalMomentsDtos);
        return result;
    }

    /**
     * 仅看圈内-朋友圈动态-查询(分页未做！)
     *
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-circle-moments")
    @ApiOperation(value = "圈内-朋友圈动态-查询")
    public AjaxResult<List<MomentsDetailDto>> getCircleMoments(@RequestBody MomentsVo momentsVo) {
        log.info("圈内-朋友圈动态-查询,请求参数：{}", momentsVo);
        AjaxResult<List<MomentsDetailDto>> result = new AjaxResult();
        List<MomentsDetailDto> momentsDetailDtos;
        // 动态内容获取
        if (TechGoalObjects.isEmpty(momentsVo.getUserId())) {
            log.error("圈内-朋友圈动态-查询,异常：{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        if (TechGoalObjects.isEmpty(momentsVo.getCircleNo())) {
            log.error("圈内-朋友圈动态-查询,异常：{}", ErrorCodeEnum.ERROR_CODE_000009.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000009);
        }
        momentsDetailDtos = momentsManageService.getCircleMoments(Long.valueOf(momentsVo.getCircleNo()));
        result.setResult(momentsDetailDtos);
        return result;
    }

    /**
     * 发现-朋友圈动态-查询(分页未做！)
     *
     * @param momentsVo 动态内容
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-discover-moments")
    @ApiOperation(value = "发现-朋友圈动态-查询")
    public AjaxResult<List<MomentsDetailDto>> getDiscoverMoments(@RequestBody MomentsVo momentsVo) {
        AjaxResult<List<MomentsDetailDto>> result = new AjaxResult();
        if (TechGoalObjects.isEmpty(momentsVo.getUserId())) {
            log.error("圈内-朋友圈动态-查询,异常：{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        // 动态内容获取
        List<MomentsDetailDto> list = momentsManageService.getDiscoverMoments(Long.valueOf(momentsVo.getUserId()));
        result.setResult(list);
        return result;
    }

    /**
     * 朋友圈动态-动态详情-查询（包括圈内动态和个人动态）
     *
     * @param momentsVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/get-moments-detail")
    @ApiOperation(value = "朋友圈动态-动态详情-查询")
    public AjaxResult<MomentsDetailDto> getMomentsDetail(@RequestBody MomentsVo momentsVo) {
        log.info("朋友圈动态-动态详情-查询,请求参数：{}", momentsVo);
        AjaxResult<MomentsDetailDto> result = new AjaxResult();
        MomentsDetailDto momentsDetailDto;
        // 动态内容获取
        if (TechGoalObjects.isEmpty(momentsVo.getUserId())) {
            log.error("朋友圈动态-动态详情-查询,异常：{}", ErrorCodeEnum.ERROR_CODE_000008.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000008);
        }
        if (TechGoalObjects.isEmpty(momentsVo.getMomentsId())) {
            log.error("朋友圈动态-动态详情-查询,异常：{}", ErrorCodeEnum.ERROR_CODE_000016.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000016);
        }
        // 判断该条动态是否有circleNo，有则是圈子动态，否则是个人动态
        if (TechGoalObjects.isEmpty(momentsVo.getCircleNo())) {
            momentsDetailDto = momentsManageService.getPersonalMomentDetail(Long.valueOf(momentsVo.getMomentsId()));
        } else {
            momentsDetailDto = momentsManageService.getCircleMomentDetail(Long.valueOf(momentsVo.getMomentsId()), Long.valueOf(momentsVo.getCircleNo()));
        }
        result.setResult(momentsDetailDto);
        return result;
    }

    /**
     * 朋友圈动态-添加评论
     *
     * @param commentVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/add-comments")
    @ApiOperation(value = "朋友圈动态-添加评论")
    public AjaxResult addComments(@RequestBody CommentVo commentVo) {
        log.info("朋友圈动态-添加评论,请求参数：{}", commentVo);
        AjaxResult result = new AjaxResult();
        ParamValidate.validate(commentVo);
        momentsManageService.addComments(commentVo);
        log.info("朋友圈动态-添加评论, 成功");
        return result;
    }

    /**
     * 朋友圈动态-点赞
     *
     * @param circlePraiseVo 请求参数
     * @return 结果
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/add-thumb-up")
    @ApiOperation(value = "朋友圈动态-点赞")
    public AjaxResult addThumbUp(@RequestBody CirclePraiseVo circlePraiseVo) {
        log.info("朋友圈动态-点赞,请求参数：{}", circlePraiseVo);
        AjaxResult result = new AjaxResult();
        ParamValidate.validate(circlePraiseVo);
        momentsManageService.addThumbUp(circlePraiseVo);
        log.info("朋友圈动态-点赞, 成功");
        return result;
    }
}
