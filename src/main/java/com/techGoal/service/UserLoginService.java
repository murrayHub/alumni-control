package com.techGoal.service;


import com.techGoal.pojo.dao.UserLogin;

import java.util.List;

/**
 * description : 用户登录业务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 13:58
 */
public interface UserLoginService {
    UserLogin getUserLoginInfo();

    void setValue(UserLogin userLogin);

    void setValue3(UserLogin userLogin);

    List<UserLogin> getUserLoginList();
}
