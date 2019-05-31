package com.techGoal.mapper;

import com.techGoal.pojo.dao.CircleMomentsDo;
import com.techGoal.pojo.dao.PersonalMomentsDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CircleMomentsMapper extends Mapper<CircleMomentsDo> {

    /**
     * 获取圈内动态
     *
     * @param circleMomentsDo 请求参数
     * @return 结果集
     */
    List<CircleMomentsDo> queryCircleMoments(CircleMomentsDo circleMomentsDo);
}