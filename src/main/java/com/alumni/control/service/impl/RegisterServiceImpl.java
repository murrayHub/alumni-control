package com.alumni.control.service.impl;

import com.alumni.control.constant.RedisDict;
import com.alumni.control.dict.NumberDict;
import com.alumni.control.dict.NumberStrDict;
import com.alumni.control.enums.ErrorCodeEnum;
import com.alumni.control.enums.InvitationEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.mapper.*;
import com.alumni.control.pojo.dao.*;
import com.alumni.control.pojo.vo.AlumniRegisterVo;
import com.alumni.control.pojo.vo.UserRegisterVo;
import com.alumni.control.redis.OrderIdManager;
import com.alumni.control.service.RegisterService;
import com.alumni.control.utils.DateUtil;
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
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserDegreeIdentifyDoMapper userDegreeIdentifyDoMapper;
    @Autowired
    private UserJobIdentifyMapper userJobIdentifyMapper;
    @Autowired
    private UserParttimeJobIdentifyMapper userParttimeJobIdentifyMapper;
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

    /**
     * 校友信息落库
     *
     * @param alumniRegisterVo 校友信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertAlumniInfo(AlumniRegisterVo alumniRegisterVo){
        // 用户基本信息
        Long userNo = orderIdManager.orderIdCreate(RedisDict.USER_INFO_KEY);
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setUserId(userNo);
        userInfoDo.setUserRealName(alumniRegisterVo.getUsername());
        userInfoDo.setGender(Integer.valueOf(alumniRegisterVo.getGender()));
        userInfoDo.setProvince(Integer.valueOf(alumniRegisterVo.getProvince()));
        userInfoDo.setCity(Integer.valueOf(alumniRegisterVo.getCity()));
        userInfoDo.setEmailAuth(NumberDict.ZERO);
        userInfoDo.setPhoneNoAuth(NumberDict.ZERO);
        userInfoDo.setWeixinNoAuth(NumberDict.ZERO);
        userInfoDo.setEmail(alumniRegisterVo.getEmail());
        userInfoDo.setPhoneNo(alumniRegisterVo.getPhoneNo());
        userInfoDo.setWeixinNo(alumniRegisterVo.getWeixinNo());
        userInfoDo.setCoreLabel(alumniRegisterVo.getCoreLabel());
        userInfoDo.setDomain(Integer.valueOf(alumniRegisterVo.getDomain()));
        userInfoDo.setRealnameStatus(NumberDict.ONE);
        userInfoDo.setCreateBy(alumniRegisterVo.getUsername());
        userInfoDo.setCreateAt(new Date());
        userInfoMapper.insert(userInfoDo);
        // 用户学历信息
        try {
            Long identifyCollegeId = orderIdManager.orderIdCreate(RedisDict.USER_DEGREE_IDENTIFY_KEY);
            UserDegreeIdentifyDo userDegreeIdentifyDo = new UserDegreeIdentifyDo();
            userDegreeIdentifyDo.setIdentifyCollegeId(identifyCollegeId);
            userDegreeIdentifyDo.setUserId(userNo);
            userDegreeIdentifyDo.setCollegeNo(alumniRegisterVo.getCollegeNo());
            userDegreeIdentifyDo.setInstituteNo(Long.valueOf(alumniRegisterVo.getInstituteNo()));
            userDegreeIdentifyDo.setEntranceTime(DateUtil.parseString2Date(alumniRegisterVo.getEntranceTime()));
            userDegreeIdentifyDo.setGraduationTime(DateUtil.parseString2Date(alumniRegisterVo.getGraduationTime()));
            userDegreeIdentifyDo.setDegreeNo(Integer.valueOf(alumniRegisterVo.getDegree()));
            userDegreeIdentifyDo.setEnabled(NumberDict.ONE);
            userDegreeIdentifyDo.setProfessionName(alumniRegisterVo.getProfession());
            userDegreeIdentifyDo.setCreateBy(alumniRegisterVo.getUsername());
            userDegreeIdentifyDo.setCreateAt(new Date());
            userDegreeIdentifyDo.setIdentifyStatus(NumberDict.ONE);
            userDegreeIdentifyDo.setIdentifyType(Integer.valueOf(alumniRegisterVo.getIdentifyType()));
            if(NumberStrDict.ONE.equals(alumniRegisterVo.getIdentifyType())){
                userDegreeIdentifyDo.setIdCardNo(alumniRegisterVo.getIdCardNo());
            }else {
                userDegreeIdentifyDo.setStudentNo(alumniRegisterVo.getStudentNo());
            }
            userDegreeIdentifyDo.setGrade(alumniRegisterVo.getGrade());
            userDegreeIdentifyDoMapper.insert(userDegreeIdentifyDo);

        // 工作经历
        int jobNum = alumniRegisterVo.getCompanyNameNor().size();
        if(jobNum > 0){
            for(int i=0;i<jobNum;i++){
                Long identifyJobId = orderIdManager.orderIdCreate(RedisDict.USER_JOB_IDENTIFY_KEY);
                UserJobIdentifyDo userJobIdentifyDo = new UserJobIdentifyDo();
                userJobIdentifyDo.setIdentifyJobId(identifyJobId);
                userJobIdentifyDo.setUserId(userNo);
                userJobIdentifyDo.setCompanyName(alumniRegisterVo.getCompanyNameNor().get(i));
                userJobIdentifyDo.setWorkStartTime(DateUtil.parseString2Date(alumniRegisterVo.getBeginTimeNor().get(i)));
                userJobIdentifyDo.setWorkEndTime(DateUtil.parseString2Date(alumniRegisterVo.getEndTimeNor().get(i)));
                userJobIdentifyDo.setPositionName(alumniRegisterVo.getPositionNor().get(i));
                userJobIdentifyDo.setEnabled(NumberDict.ONE);
                userJobIdentifyDo.setCreateBy(alumniRegisterVo.getUsername());
                userJobIdentifyDo.setCreateAt(new Date());
                userJobIdentifyMapper.insert(userJobIdentifyDo);
            }
        }
        // 兼职经历
        int jobPTNum = alumniRegisterVo.getCompanyNamePT().size();
        if(jobPTNum > 0){
            for(int j=0;j<jobPTNum;j++){
                Long identifyJobPtId = orderIdManager.orderIdCreate(RedisDict.USER_PART_TIME_JOB_IDENTIFY_KEY);
                UserParttimeJobIdentifyDo userParttimeJobIdentifyDo = new UserParttimeJobIdentifyDo();
                userParttimeJobIdentifyDo.setIdentifyParttimeJobId(identifyJobPtId);
                userParttimeJobIdentifyDo.setUserId(userNo);
                userParttimeJobIdentifyDo.setCompanyName(alumniRegisterVo.getCompanyNamePT().get(j));
                userParttimeJobIdentifyDo.setWorkStartTime(DateUtil.parseString2Date(alumniRegisterVo.getBeginTimePT().get(j)));
                userParttimeJobIdentifyDo.setWorkEndTime(DateUtil.parseString2Date(alumniRegisterVo.getEndTimePT().get(j)));
                userParttimeJobIdentifyDo.setPositionName(alumniRegisterVo.getPositionPT().get(j));
                userParttimeJobIdentifyDo.setEnabled(NumberDict.ONE);
                userParttimeJobIdentifyDo.setCreateBy(alumniRegisterVo.getUsername());
                userParttimeJobIdentifyDo.setCreateAt(new Date());
                userParttimeJobIdentifyMapper.insert(userParttimeJobIdentifyDo);
            }
        }
        } catch (Exception e) {
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000039);
        }
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
