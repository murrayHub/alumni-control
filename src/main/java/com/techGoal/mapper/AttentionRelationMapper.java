package com.techGoal.mapper;

import com.techGoal.pojo.dao.AttentionRelationDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AttentionRelationMapper extends Mapper<AttentionRelationDo> {
    /**
     * 查询两者是否已存在关注关系
     * @param attentionRelationDo 请求参数
     * @return 结果
     */
    List<AttentionRelationDo> selectRelation(AttentionRelationDo attentionRelationDo);

    /**
     * 更新两者关注关系
     * @param attentionRelationDo 请求参数
     */
    void updateRelation(AttentionRelationDo attentionRelationDo);
}