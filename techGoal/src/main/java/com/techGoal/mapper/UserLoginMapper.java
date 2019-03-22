package com.techGoal.mapper;

import com.techGoal.pojo.dao.UserLogin;

public interface UserLoginMapper {
    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);
}