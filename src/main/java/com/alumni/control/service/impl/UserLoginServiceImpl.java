package com.alumni.control.service.impl;

import com.alumni.control.dict.NumberDict;
import com.alumni.control.dict.NumberStrDict;
import com.alumni.control.enums.ErrorCodeEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.mapper.LoginInfoMapper;
import com.alumni.control.mapper.UserLoginLogMapper;
import com.alumni.control.mapper.UserPwdMapper;
import com.alumni.control.pojo.dao.LoginInfoDo;
import com.alumni.control.pojo.dao.UserLoginLogDo;
import com.alumni.control.pojo.dao.UserPwdDo;
import com.alumni.control.pojo.vo.UserLoginVo;
import com.alumni.control.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * description : 用户注册登录-服务层-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/26 9:35
 */
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {
    /**
     * 用户登录信息 Mapper
     */
    @Autowired
    private LoginInfoMapper loginInfoMapper;
    /**
     * 用户登录密码 Mapper
     */
    @Autowired
    private UserPwdMapper userPwdMapper;
    /**
     * 用户登录日志 Mapper
     */
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    /**
     * 核对用户登录账号信息
     *
     * @param userLoginVo 用户登陆信息
     * @return LoginInfoDo 用户登陆信息
     */
    @Override
    public LoginInfoDo verifyUserLoginInfo(UserLoginVo userLoginVo) {
        LoginInfoDo loginInfoDo = new LoginInfoDo();
        loginInfoDo.setLoginNo(userLoginVo.getLoginNo());
        List<LoginInfoDo> result = loginInfoMapper.select(loginInfoDo);
        if (CollectionUtils.isEmpty(result)) {
            log.error("核对用户登录账号信息,异常:{}", ErrorCodeEnum.ERROR_CODE_000020.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000020);
        }
        LoginInfoDo loginInfoDo1 = result.get(NumberDict.ZERO);
        if (!NumberStrDict.ONE.equals(loginInfoDo1.getLoginState())) {
            log.error("核对用户登录账号信息,异常:{}", ErrorCodeEnum.ERROR_CODE_000021.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000021);
        }
        Long userId = loginInfoDo1.getUserId();
        UserPwdDo userPwdDo = new UserPwdDo();
        userPwdDo.setUserId(userId);
        UserPwdDo userPwdDo1 = userPwdMapper.selectOne(userPwdDo);
        if (userPwdDo1.getState() != NumberDict.ONE) {
            log.error("核对用户登录账号信息,异常:{}", ErrorCodeEnum.ERROR_CODE_000022.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000022);
        }
        if (!userLoginVo.getPwd().equals(userPwdDo1.getPwd())) {
            log.error("核对用户登录账号信息,异常:{}", ErrorCodeEnum.ERROR_CODE_000023.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000023);
        }
        return loginInfoDo1;
    }

    /**
     * 插入登录日志
     *
     * @param loginInfoDo 用户登录信息
     */
    @Override
    public void insertLoginLog(LoginInfoDo loginInfoDo) {
        UserLoginLogDo userLoginLogDo = new UserLoginLogDo();
        userLoginLogDo.setUserId(loginInfoDo.getUserId());
        userLoginLogDo.setLoginNo(loginInfoDo.getLoginNo());
        userLoginLogDo.setLoginType(Integer.valueOf(loginInfoDo.getLoginType()));
        userLoginLogDo.setLoginState(Integer.valueOf(loginInfoDo.getLoginState()));
        userLoginLogDo.setState(NumberDict.ONE);
        userLoginLogDo.setCreateBy(loginInfoDo.getLoginNo());
        userLoginLogDo.setCreateAt(new Date());
        userLoginLogMapper.insert(userLoginLogDo);
    }
}
