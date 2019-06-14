package com.techGoal.service;

import com.techGoal.pojo.vo.UserInfoVo;

import java.util.List;

/**
 * description : 用户个人信息-服务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 9:17
 */
public interface UserInfoService {

    /**
     * 查询用户个人信息
     * @param userInfoVo 用户个人信息
     * @return 结果
     */
    UserInfoVo queryUserInfo(UserInfoVo userInfoVo);
    /**
     * 修改用户个人信息
     * @param userInfoVo 用户个人信息
     */
    void modifyUserInfo(UserInfoVo userInfoVo);

    /**
     * 修改用户个人画像
     * @param userInfoVo 用户个人信息
     */
    void modifyUserProfile(UserInfoVo userInfoVo);

    /**
     * 通过用户名称<模糊查询>平台所有符合条件的用户
     * @param userInfoVo 用户个人信息
     * @return 结果集
     */
    List<UserInfoVo> queryUserByName(UserInfoVo userInfoVo);
}
