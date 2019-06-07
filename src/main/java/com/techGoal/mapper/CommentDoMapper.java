package com.techGoal.mapper;

import com.techGoal.pojo.dao.CommentDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CommentDoMapper extends Mapper<CommentDo> {
    /**
     * 获取动态的所有评论
     * @param commentDo 检索参数
     * @return 结果集
     */
    List<CommentDo> getAllComments(CommentDo commentDo);
}