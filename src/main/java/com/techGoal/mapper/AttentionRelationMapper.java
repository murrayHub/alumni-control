package com.techGoal.mapper;

import com.techGoal.pojo.dao.AttentionRelationDo;
import com.techGoal.pojo.dao.UserInfoDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AttentionRelationMapper extends Mapper<AttentionRelationDo> {
    /**
     * 查询两者是否已存在关注关系
     *
     * @param attentionRelationDo 请求参数
     * @return 结果
     */
    List<AttentionRelationDo> selectRelation(AttentionRelationDo attentionRelationDo);

    /**
     * 更新两者关注关系
     *
     * @param attentionRelationDo 请求参数
     */
    void updateRelation(AttentionRelationDo attentionRelationDo);

    /**
     * 更新两者关注关系
     *
     * @param attentionRelationDo 请求参数
     */
    void updateRelationDirect(AttentionRelationDo attentionRelationDo);

    /**
     * 获取所有我关注的好友
     *
     * @param attentionRelationDo 请求参数
     * @return 结果集
     */
    List<UserInfoDo> getPayAttentionUsers(AttentionRelationDo attentionRelationDo);

    /**
     * 获取所有关注我的好友
     *
     * @param attentionRelationDo 请求参数
     * @return 结果集
     */
    List<UserInfoDo> getBePayedAttentionUsers(AttentionRelationDo attentionRelationDo);

    /**
     * 获取所有尚未关注的用户
     *
     * @param attentionRelationDo 请求参数
     * @return 结果集
     */
    List<UserInfoDo> getNoPayAttentionUsers(AttentionRelationDo attentionRelationDo);


}