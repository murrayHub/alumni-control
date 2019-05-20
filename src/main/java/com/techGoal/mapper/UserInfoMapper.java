package com.techGoal.mapper;

import com.techGoal.pojo.dao.UserInfoDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserInfoMapper extends Mapper<UserInfoDo> {

    /**
     * 修改用户个人信息
     *
     * @param userInfoDo 用户个人信息
     */
    void modifyUserInfo(UserInfoDo userInfoDo);

    /**
     * 通过用户名称<模糊查询>平台所有符合条件的用户
     * @param userInfoDo 用户个人信息
     * @return 结果集
     */
    List<UserInfoDo> queryUserByName(UserInfoDo userInfoDo);
}