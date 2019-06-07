package com.techGoal.mapper;

import com.techGoal.pojo.dao.MomentsTimeAxisDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MomentsTimeAxisMapper extends Mapper<MomentsTimeAxisDo> {

    /**
     * 获取发现-所有动态
     * @param momentsTimeAxisDo 请求参数
     * @return 结果集
     */
    List<MomentsTimeAxisDo> queryDiscoverMoments(MomentsTimeAxisDo momentsTimeAxisDo);

    /**
     * 获取个人发布-所有动态
     * @param momentsTimeAxisDo 请求参数
     * @return 结果集
     */
    List<MomentsTimeAxisDo> queryPersonalMoments(MomentsTimeAxisDo momentsTimeAxisDo);
}