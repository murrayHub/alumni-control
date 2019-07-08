package com.alumni.control.service;

import com.alumni.control.pojo.vo.AlumniRegisterVo;
import com.alumni.control.pojo.vo.UserRegisterVo;

/**
 * description : 注册-服务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/25 17:45
 */
public interface RegisterService {

    /**
     * 用户注册信息落库
     * @param userRegisterVo
     */
    void insertUserInfo(UserRegisterVo userRegisterVo);

    /**
     * 查询用户登录账号是否存在
     *
     * @param userRegisterVo 用户登陆信息
     * @return 记录数
     */
    int checkUserLoginInfo(UserRegisterVo userRegisterVo);

    /**
     * 校友信息落库
     * @param alumniRegisterVo 校友信息
     */
    void insertAlumniInfo(AlumniRegisterVo alumniRegisterVo);

}
