package com.alumni.control.service;


import com.alumni.control.pojo.dao.LoginInfoDo;
import com.alumni.control.pojo.vo.UserLoginVo;

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
     * 核对用户登录账号信息
     *
     * @param userLoginVo 用户登录信息
     * @return  LoginInfoDo 用户登录信息
     */
    LoginInfoDo verifyUserLoginInfo(UserLoginVo userLoginVo);
    /**
     * 插入登录日志
     * @param loginInfoDo 用户登录信息
     */
    void insertLoginLog(LoginInfoDo loginInfoDo);
}
