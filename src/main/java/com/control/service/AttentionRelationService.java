package com.control.service;

import com.control.pojo.vo.AttentionRelationVo;
import com.control.pojo.vo.UserInfoVo;
import com.techGoal.pojo.vo.AttentionRelationVo;
import com.techGoal.pojo.vo.UserInfoVo;

import java.util.List;

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

    /**
     * 获取所有我关注的好友
     * @param attentionRelationVo 请求参数
     * @return 结果集
     */
    List<UserInfoVo> getPayAttentionUsers(AttentionRelationVo attentionRelationVo);

    /**
     * 获取所有关注我的好友
     * @param attentionRelationVo 请求参数
     * @return 结果集
     */
    List<UserInfoVo> getBePayedAttentionUsers(AttentionRelationVo attentionRelationVo);

    /**
     * 获取所有尚未关注的用户
     * @param attentionRelationVo 请求参数
     * @return 结果集
     */
    List<UserInfoVo> getNoPayAttentionUsers(AttentionRelationVo attentionRelationVo);
}
