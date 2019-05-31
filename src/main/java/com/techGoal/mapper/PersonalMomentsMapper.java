package com.techGoal.mapper;

import com.techGoal.pojo.dao.PersonalMomentsDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PersonalMomentsMapper extends Mapper<PersonalMomentsDo> {
    /**
     * 获取个人动态
     *
     * @param personalMomentsDo 请求参数
     * @return 结果集
     */
    List<PersonalMomentsDo> queryPersonalMoments(PersonalMomentsDo personalMomentsDo);
}