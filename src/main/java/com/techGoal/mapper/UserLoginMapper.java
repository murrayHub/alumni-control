package com.techGoal.mapper;

import com.techGoal.pojo.dao.UserLogin;
import tk.mybatis.mapper.common.Mapper;

public interface UserLoginMapper extends Mapper<UserLogin> {
    void setValue3(UserLogin userLogin);
}