package com.techGoal.service.impl;

import com.google.common.collect.Lists;
import com.techGoal.convert.UserInfoConvert;
import com.techGoal.dict.CommonDict;
import com.techGoal.dict.NumberDict;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.mapper.UserInfoMapper;
import com.techGoal.pojo.dao.UserInfoDo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.service.UserInfoService;
import com.techGoal.utils.TechGoalObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * description : 用户个人信息-服务层-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 9:18
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    /**
     * 用户信息 Mapper
     */
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 查询用户个人信息
     * @param userInfoVo 用户个人信息
     * @return 结果
     */
    @Override
    public UserInfoVo queryUserInfo(UserInfoVo userInfoVo) {
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setUserId(Long.valueOf(userInfoVo.getUserId()));
        UserInfoDo userInfoDo1 = userInfoMapper.selectOne(userInfoDo);
        UserInfoVo userInfo = UserInfoConvert.UserInfoDoToVo(userInfoDo1);
        return userInfo;
    }

    /**
     * 修改用户个人信息
     *
     * @param userInfoVo 用户个人信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyUserInfo(UserInfoVo userInfoVo) {
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
        userInfoDo.setUpdateBy(CommonDict.SYSTEM);
        // 标签个数最大为6
        String[] labels = userInfoVo.getCoreLabel().split(",");
        if (labels.length > NumberDict.SIX) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000007);
        }
        // 检查邮箱、手机号、微信号是否已存在
        UserInfoDo userInfoDo1 = new UserInfoDo();
        userInfoDo1.setEmail(userInfoDo.getEmail());
        List<UserInfoDo> userInfoDos1 = userInfoMapper.select(userInfoDo1);
        if (!CollectionUtils.isEmpty(userInfoDos1)) {
            if (!userInfoDos1.get(0).getUserId().equals(userInfoDo.getUserId())) {
                throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000004);
            }
        }
        UserInfoDo userInfoDo2 = new UserInfoDo();
        userInfoDo2.setPhoneNo(userInfoDo.getPhoneNo());
        List<UserInfoDo> userInfoDos2 = userInfoMapper.select(userInfoDo2);
        if (!CollectionUtils.isEmpty(userInfoDos2)) {
            if (!userInfoDos2.get(0).getUserId().equals(userInfoDo.getUserId())) {
                throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000005);
            }
        }
        UserInfoDo userInfoDo3 = new UserInfoDo();
        userInfoDo3.setWeixinNo(userInfoDo.getWeixinNo());
        List<UserInfoDo> userInfoDos3 = userInfoMapper.select(userInfoDo3);
        if (!CollectionUtils.isEmpty(userInfoDos3)) {
            if (!userInfoDos3.get(0).getUserId().equals(userInfoDo.getUserId())) {
                throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000006);
            }
        }
        userInfoMapper.modifyUserInfo(userInfoDo);
    }

    /**
     * 修改用户个人画像
     *
     * @param userInfoVo 用户个人信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyUserProfile(UserInfoVo userInfoVo) {
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setUserId(userInfoDo.getUserId());
        userInfoMapper.modifyUserInfo(userInfoDo);
    }

    /**
     * 通过用户名称<模糊查询>平台所有符合条件的用户
     *
     * @param userInfoVo 用户个人信息
     * @return 结果集
     */
    @Override
    public List<UserInfoVo> queryUserByName(UserInfoVo userInfoVo) {
        List<UserInfoVo> userInfoVoList = Lists.newArrayList();
        UserInfoDo userInfoDo = new UserInfoDo();
        if(TechGoalObjects.isEmpty(userInfoVo.getUserRealName())){
            return userInfoVoList;
        }
        userInfoDo.setUserRealName(userInfoVo.getUserRealName());
        List<UserInfoDo> userInfoDos = userInfoMapper.queryUserByName(userInfoDo);
        for(UserInfoDo userInfoDo1 : userInfoDos){
            UserInfoVo userInfoVo1 = UserInfoConvert.UserInfoDoToVo(userInfoDo1);
            userInfoVoList.add(userInfoVo1);
        }
        return userInfoVoList;
    }
}
