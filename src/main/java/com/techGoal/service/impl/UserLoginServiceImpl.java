package com.techGoal.service.impl;

import com.techGoal.constant.RedisDict;
import com.techGoal.dict.NumberDict;
import com.techGoal.dict.NumberStrDict;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.mapper.LoginInfoMapper;
import com.techGoal.mapper.UserInfoMapper;
import com.techGoal.mapper.UserPwdMapper;
import com.techGoal.pojo.dao.LoginInfoDo;
import com.techGoal.pojo.dao.UserInfoDo;
import com.techGoal.pojo.dao.UserPwdDo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.pojo.vo.UserRegisterOneStepVo;
import com.techGoal.redis.OrderIdManager;
import com.techGoal.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * @date : 2019/5/19 21:09
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
     * 用户信息 Mapper
     */
    @Autowired
    private UserInfoMapper userInfoMapper;
    /**
     * 流水号创建服务
     */
    @Autowired
    private OrderIdManager orderIdManager;

    /**
     * 查询用户登录账号是否存在
     *
     * @param userRegisterOneStepVo 用户登陆信息
     * @return 记录数
     */
    @Override
    public int checkUserLoginInfo(UserRegisterOneStepVo userRegisterOneStepVo) {
        LoginInfoDo loginInfoDo = new LoginInfoDo();
        loginInfoDo.setLoginNo(userRegisterOneStepVo.getLoginNo());
        List<LoginInfoDo> result = loginInfoMapper.select(loginInfoDo);
        return result.size();
    }

    /**
     * 插入用户登陆账号信息
     *
     * @param userRegisterOneStepVo 用户登陆信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserLoginInfo(UserRegisterOneStepVo userRegisterOneStepVo) {
        Long userNo = orderIdManager.orderIdCreate(RedisDict.USER_INFO_KEY);
        LoginInfoDo loginInfoDo = new LoginInfoDo();
        loginInfoDo.setLoginNo(userRegisterOneStepVo.getLoginNo());
        loginInfoDo.setLoginType(userRegisterOneStepVo.getLoginType());
        loginInfoDo.setUserId(userNo);
        loginInfoDo.setLoginState(NumberStrDict.ONE);
        loginInfoDo.setCreateAt(new Date());
        loginInfoDo.setCreateBy(String.valueOf(userNo));
        loginInfoMapper.insert(loginInfoDo);

        UserPwdDo userPwdDo = new UserPwdDo();
        userPwdDo.setUserId(userNo);
        userPwdDo.setPwd(userRegisterOneStepVo.getPwd());
        userPwdDo.setPwdType(NumberDict.ONE);
        userPwdDo.setState(NumberDict.ONE);
        userPwdDo.setCreateAt(new Date());
        userPwdDo.setCreateBy(String.valueOf(userNo));
        userPwdMapper.insert(userPwdDo);
    }

    /**
     * 插入用户个人信息
     *
     * @param userInfoVo 用户个人信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserInfo(UserInfoVo userInfoVo) {
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setUserId(Long.valueOf(userInfoVo.getUserId()));
        userInfoDo.setUserRealName(userInfoVo.getUserRealName());
        userInfoDo.setGender(Integer.valueOf(userInfoVo.getGender()));
        userInfoDo.setProvince(Integer.valueOf(userInfoVo.getProvince()));
        userInfoDo.setCity(Integer.valueOf(userInfoVo.getCity()));
        userInfoDo.setEmail(userInfoVo.getEmail());
        userInfoDo.setPhoneNo(userInfoVo.getPhoneNo());
        userInfoDo.setWeixinNo(userInfoVo.getWeixinNo());
        userInfoDo.setEmailAuth(Integer.valueOf(userInfoVo.getEmailAuth()));
        userInfoDo.setPhoneNoAuth(Integer.valueOf(userInfoVo.getPhoneNoAuth()));
        userInfoDo.setWeixinNoAuth(Integer.valueOf(userInfoVo.getWeixinNoAuth()));
        userInfoDo.setCoreLabel(userInfoVo.getCoreLabel());
        userInfoDo.setDomain(Integer.valueOf(userInfoVo.getDomain()));
        userInfoDo.setRealnameStatus(NumberDict.ZERO);
        userInfoDo.setCreateAt(new Date());
        userInfoDo.setCreateBy(userInfoVo.getUserId());
        // 标签个数最大为6
        String[] labels = userInfoVo.getCoreLabel().split(",");
        if (labels.length > NumberDict.SIX) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000007);
        }
        // 检查邮箱、电话、微信号是否存在
        UserInfoDo userInfoDo1 = new UserInfoDo();
        userInfoDo1.setEmail(userInfoDo.getEmail());
        List<UserInfoDo> userInfoDos1 = userInfoMapper.select(userInfoDo1);
        if (!CollectionUtils.isEmpty(userInfoDos1)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000004);
        }
        UserInfoDo userInfoDo2 = new UserInfoDo();
        userInfoDo2.setPhoneNo(userInfoDo.getPhoneNo());
        List<UserInfoDo> userInfoDos2 = userInfoMapper.select(userInfoDo2);
        if (!CollectionUtils.isEmpty(userInfoDos2)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000005);
        }
        UserInfoDo userInfoDo3 = new UserInfoDo();
        userInfoDo3.setWeixinNo(userInfoDo.getWeixinNo());
        List<UserInfoDo> userInfoDos3 = userInfoMapper.select(userInfoDo3);
        if (!CollectionUtils.isEmpty(userInfoDos3)) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000006);
        }
        userInfoMapper.insert(userInfoDo);
    }
}
