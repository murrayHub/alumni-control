package com.techGoal.mapper;

import com.techGoal.pojo.dao.CirclePraiseDo;
import com.techGoal.pojo.dao.CommentDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CirclePraiseMapper extends Mapper<CirclePraiseDo> {

    /**
     * 获取动态的所有点赞
     * @param circlePraiseDo CirclePraiseDo
     * @return 结果集
     */
    List<CirclePraiseDo> getAllPraises(CirclePraiseDo circlePraiseDo);
}