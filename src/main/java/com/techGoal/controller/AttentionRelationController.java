package com.techGoal.controller;

import com.techGoal.pojo.vo.AttentionRelationVo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.service.AttentionRelationService;
import com.techGoal.utils.validation.ParamValidate;
import com.techGoal.utils.web.AjaxResult;
import com.techGoal.utils.web.WebEnhance;
import com.techGoal.utils.web.WebResultModeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * 加关注
     */
    @WebEnhance(mode = WebResultModeEnum.AJAX)
    @PostMapping("/pay-attention")
    @ApiOperation(value = "加关注")
    public AjaxResult payAttention(@RequestBody AttentionRelationVo attentionRelationVo){
        AjaxResult result = new AjaxResult();
        log.info("加关注,请求参数:{}", attentionRelationVo);
        ParamValidate.validate(attentionRelationVo);
        attentionRelationService.payAttention(attentionRelationVo);
        log.info("加关注,成功，请求参数:{}", attentionRelationVo);
        return result;
    }
}
