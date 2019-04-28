package com.techGoal.service.impl;

import com.techGoal.mapper.UserLoginMapper;
import com.techGoal.pojo.dao.UserLogin;
import com.techGoal.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description : 用户登录业务层 实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 14:00
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Override
    public UserLogin getUserLoginInfo() {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(1);
        UserLogin userLogin2 = userLoginMapper.selectOne(userLogin);
        return userLogin2;
    }

    @Override
    public void setValue(UserLogin userLogin) {
        userLoginMapper.updateByPrimaryKey(userLogin);
    }

    @Override
    public void setValue3(UserLogin userLogin) {
        userLoginMapper.setValue3(userLogin);
    }

    @Override
    public List<UserLogin> getUserLoginList() {
        List<UserLogin> list = userLoginMapper.selectAll();
        return list;
    }
}
