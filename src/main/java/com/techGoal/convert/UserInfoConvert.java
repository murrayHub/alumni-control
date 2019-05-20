package com.techGoal.convert;

import com.techGoal.pojo.dao.UserInfoDo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.utils.TechGoalObjects;

/**
 * description : 用户信息管理
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 13:59
 */
public class UserInfoConvert {

    public static UserInfoDo UserInfoVoToDo(UserInfoVo userInfoVo) {
        if (userInfoVo == null) {
            return null;
        }
        UserInfoDo userInfoDo = new UserInfoDo();
        if(!TechGoalObjects.isEmpty(userInfoVo.getUserId())){
            userInfoDo.setUserId(Long.valueOf(userInfoVo.getUserId()));
        }
        userInfoDo.setUserRealName(userInfoVo.getUserRealName());
        if(!TechGoalObjects.isEmpty(userInfoVo.getGender())){
            userInfoDo.setGender(Integer.valueOf(userInfoVo.getGender()));
        }
        if(!TechGoalObjects.isEmpty(userInfoVo.getProvince())){
            userInfoDo.setProvince(Integer.valueOf(userInfoVo.getProvince()));
        }
        if(!TechGoalObjects.isEmpty(userInfoVo.getCity())){
            userInfoDo.setCity(Integer.valueOf(userInfoVo.getCity()));
        }
        userInfoDo.setEmail(userInfoVo.getEmail());
        userInfoDo.setPhoneNo(userInfoVo.getPhoneNo());
        userInfoDo.setWeixinNo(userInfoVo.getWeixinNo());
        if(!TechGoalObjects.isEmpty(userInfoVo.getEmailAuth())){
            userInfoDo.setEmailAuth(Integer.valueOf(userInfoVo.getEmailAuth()));
        }
        if(!TechGoalObjects.isEmpty(userInfoVo.getPhoneNoAuth())){
            userInfoDo.setPhoneNoAuth(Integer.valueOf(userInfoVo.getPhoneNoAuth()));
        }
        if(!TechGoalObjects.isEmpty(userInfoVo.getWeixinNoAuth())){
            userInfoDo.setWeixinNoAuth(Integer.valueOf(userInfoVo.getWeixinNoAuth()));
        }
        userInfoDo.setCoreLabel(userInfoVo.getCoreLabel());
        if(!TechGoalObjects.isEmpty(userInfoVo.getDomain())){
            userInfoDo.setDomain(Integer.valueOf(userInfoVo.getDomain()));
        }
        return userInfoDo;
    }

    public static UserInfoVo UserInfoDoToVo(UserInfoDo userInfoDo) {
        if (userInfoDo == null) {
            return null;
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        if(!TechGoalObjects.isEmpty(userInfoVo.getUserId())){
            userInfoVo.setUserId(String.valueOf(userInfoDo.getUserId()));
        }
        userInfoVo.setUserRealName(userInfoDo.getUserRealName());
        if(!TechGoalObjects.isEmpty(userInfoVo.getGender())){
            userInfoVo.setGender(String.valueOf(userInfoDo.getGender()));
        }

        if(!TechGoalObjects.isEmpty(userInfoVo.getProvince())){
            userInfoVo.setProvince(String.valueOf(userInfoDo.getProvince()));
        }
        if(!TechGoalObjects.isEmpty(userInfoVo.getCity())){
            userInfoVo.setCity(String.valueOf(userInfoDo.getCity()));
        }
        userInfoVo.setPersonImage(userInfoDo.getPersonImage());
        userInfoVo.setHomeImage(userInfoDo.getHomeImage());
        userInfoVo.setEmail(userInfoDo.getEmail());
        userInfoVo.setPhoneNo(userInfoDo.getPhoneNo());
        userInfoVo.setWeixinNo(userInfoDo.getWeixinNo());
        if(!TechGoalObjects.isEmpty(userInfoVo.getEmailAuth())){
            userInfoVo.setEmailAuth(String.valueOf(userInfoDo.getEmailAuth()));
        }
        if(!TechGoalObjects.isEmpty(userInfoVo.getPhoneNoAuth())){
            userInfoVo.setPhoneNoAuth(String.valueOf(userInfoDo.getPhoneNoAuth()));
        }
        if(!TechGoalObjects.isEmpty(userInfoVo.getWeixinNoAuth())){
            userInfoVo.setWeixinNoAuth(String.valueOf(userInfoDo.getWeixinNoAuth()));
        }
        userInfoVo.setCoreLabel(userInfoDo.getCoreLabel());
        if(!TechGoalObjects.isEmpty(userInfoVo.getDomain())){
            userInfoVo.setDomain(String.valueOf(userInfoDo.getDomain()));
        }
        if(!TechGoalObjects.isEmpty(userInfoVo.getRealnameStatus())){
            userInfoVo.setRealnameStatus(String.valueOf(userInfoDo.getRealnameStatus()));
        }
        userInfoVo.setCreateBy(userInfoDo.getCreateBy());
        userInfoVo.setUpdateBy(userInfoDo.getUpdateBy());
        userInfoVo.setCreateAt(userInfoDo.getCreateAt());
        userInfoVo.setUpdateAt(userInfoDo.getUpdateAt());
        return userInfoVo;
    }
}
