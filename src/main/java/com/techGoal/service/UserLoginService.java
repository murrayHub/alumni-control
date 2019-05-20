package com.techGoal.service;

import com.techGoal.pojo.vo.UserRegisterOneStepVo;
import com.techGoal.pojo.vo.UserInfoVo;

/**
 * description : 用户注册登录-服务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 21:06
 */
public interface UserLoginService {

    /**
     * 查询用户登录账号是否存在
     * @param userRegisterOneStepVo 用户登陆信息
     * @return 记录数
     */
    int checkUserLoginInfo(UserRegisterOneStepVo userRegisterOneStepVo);

    /**
     * 插入用户登陆账号信息
     * @param userRegisterOneStepVo 用户登陆信息
     */
    void insertUserLoginInfo(UserRegisterOneStepVo userRegisterOneStepVo);

    /**
     * 插入用户个人信息
     * @param userInfoVo 用户个人信息
     */
    void insertUserInfo(UserInfoVo userInfoVo);
}
