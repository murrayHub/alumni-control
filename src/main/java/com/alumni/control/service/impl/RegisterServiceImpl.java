package com.alumni.control.service.impl;

import com.alumni.control.constant.RedisDict;
import com.alumni.control.dict.NumberDict;
import com.alumni.control.enums.InvitationEnum;
import com.alumni.control.mapper.AlumniManagerInfoDoMapper;
import com.alumni.control.mapper.LoginInfoMapper;
import com.alumni.control.mapper.UserPwdMapper;
import com.alumni.control.pojo.dao.AlumniManagerInfoDo;
import com.alumni.control.pojo.dao.LoginInfoDo;
import com.alumni.control.pojo.dao.UserPwdDo;
import com.alumni.control.pojo.vo.UserRegisterVo;
import com.alumni.control.redis.OrderIdManager;
import com.alumni.control.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * description : 注册-服务层-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/25 17:50
 */

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private AlumniManagerInfoDoMapper alumniManagerInfoDoMapper;
    @Autowired
    private LoginInfoMapper loginInfoMapper;
    @Autowired
    private UserPwdMapper userPwdMapper;
    /**
     * 流水号创建服务
     */
    @Autowired
    private OrderIdManager orderIdManager;

    /**
     * 用户注册信息落库
     *
     * @param userRegisterVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserInfo(UserRegisterVo userRegisterVo) {
        // 用户信息
        Long userNo = orderIdManager.orderIdCreate(RedisDict.USER_INFO_KEY);
        String invitationCode = InvitationEnum.getDescByCode(userRegisterVo.getInvitation());
        AlumniManagerInfoDo manager = this.getManagerAttr(invitationCode);
        AlumniManagerInfoDo alumniManagerInfoDo = new AlumniManagerInfoDo();
        alumniManagerInfoDo.setUserId(userNo);
        alumniManagerInfoDo.setUserRealName(userRegisterVo.getUsername());
        alumniManagerInfoDo.setPhoneNo(userRegisterVo.getAccountNo());
        alumniManagerInfoDo.setCollegeNo(userRegisterVo.getCollege());
        alumniManagerInfoDo.setManagerLevel(manager.getManagerLevel());
        alumniManagerInfoDo.setManagerType(manager.getManagerType());
        alumniManagerInfoDo.setInstituteNo(userRegisterVo.getInstitute());
        alumniManagerInfoDo.setInvitationCode(invitationCode);
        alumniManagerInfoDoMapper.insert(alumniManagerInfoDo);
        // 用户登录信息
        LoginInfoDo loginInfoDo = new LoginInfoDo();
        loginInfoDo.setUserId(userNo);
        loginInfoDo.setLoginNo(userRegisterVo.getAccountNo());
        loginInfoDo.setLoginType(String.valueOf(NumberDict.ONE));
        loginInfoDo.setLoginState(String.valueOf(NumberDict.ONE));
        loginInfoDo.setCreateAt(new Date());
        loginInfoDo.setCreateBy(userRegisterVo.getUsername());
        loginInfoMapper.insert(loginInfoDo);
        // 用户密码
        UserPwdDo userPwdDo = new UserPwdDo();
        userPwdDo.setUserId(userNo);
        userPwdDo.setPwd(userRegisterVo.getPass());
        userPwdDo.setPwdType(NumberDict.ONE);
        userPwdDo.setState(NumberDict.ONE);
        userPwdDo.setCreateAt(new Date());
        userPwdDo.setCreateBy(userRegisterVo.getUsername());
        userPwdMapper.insert(userPwdDo);
    }

    /**
     * 查询用户登录账号是否存在
     *
     * @param userRegisterVo@return 记录数
     */
    @Override
    public int checkUserLoginInfo(UserRegisterVo userRegisterVo) {
        LoginInfoDo loginInfoDo = new LoginInfoDo();
        loginInfoDo.setLoginNo(userRegisterVo.getAccountNo());
        List<LoginInfoDo> result = loginInfoMapper.select(loginInfoDo);
        return result.size();
    }

    private AlumniManagerInfoDo getManagerAttr(String invitationCode){
        AlumniManagerInfoDo alumniManagerInfoDo = new AlumniManagerInfoDo();
        if("UCAS01".equals(invitationCode)){
            alumniManagerInfoDo.setManagerLevel(NumberDict.ONE);
            alumniManagerInfoDo.setManagerType(NumberDict.ONE);
        }else if("UCAS02".equals(invitationCode)){
            alumniManagerInfoDo.setManagerLevel(NumberDict.ONE);
            alumniManagerInfoDo.setManagerType(NumberDict.TWO);
        }else if("UCAS03".equals(invitationCode)){
            alumniManagerInfoDo.setManagerLevel(NumberDict.TWO);
            alumniManagerInfoDo.setManagerType(NumberDict.ONE);
        }else{
            alumniManagerInfoDo.setManagerLevel(NumberDict.TWO);
            alumniManagerInfoDo.setManagerType(NumberDict.ONE);
        }
        return alumniManagerInfoDo;
    }
}
