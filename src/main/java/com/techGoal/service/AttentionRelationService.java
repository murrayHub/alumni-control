package com.techGoal.service;

import com.techGoal.pojo.vo.AttentionRelationVo;

/**
 * description : 关注和被关注者关系管理-服务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 22:23
 */
public interface AttentionRelationService {
    /**
     * 加关注
     * @param attentionRelationVo 请求参数
     */
    void payAttention(AttentionRelationVo attentionRelationVo);

    /**
     * 取消关注
     * @param attentionRelationVo 请求参数
     */
    void cancelPayAttention(AttentionRelationVo attentionRelationVo);
}
