package com.alumni.control.service.impl;

import com.alumni.control.convert.UserDegreeIdentifyConvert;
import com.alumni.control.dict.NumberDict;
import com.alumni.control.mapper.*;
import com.alumni.control.pojo.dao.*;
import com.alumni.control.pojo.dto.JobsInfoDto;
import com.alumni.control.pojo.dto.PartTimeJobsDto;
import com.alumni.control.pojo.vo.AlumniInfoUpdVo;
import com.alumni.control.pojo.vo.AlumniManagerInfoVo;
import com.alumni.control.pojo.vo.LevelOneAlumniUpdInfoVo;
import com.alumni.control.pojo.vo.UserDegreeIdentifyVo;
import com.alumni.control.service.AlumniManageService;
import com.alumni.control.utils.DateUtil;
import com.alumni.control.utils.TechGoalObjects;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * description : 校友信息管理-服务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 11:36
 */
@Slf4j
@Service
public class AlumniManageServiceImpl implements AlumniManageService {

    @Autowired
    private UserDegreeIdentifyDoMapper userDegreeIdentifyDoMapper;

    @Autowired
    private UserJobIdentifyMapper userJobIdentifyMapper;

    @Autowired
    private UserParttimeJobIdentifyMapper userParttimeJobIdentifyMapper;
    /**
     * 校友会管理员
     */
    @Autowired
    private AlumniManagerInfoDoMapper alumniManagerInfoDoMapper;

    @Autowired
    private LevelOneIdentifyDoMapper levelOneIdentifyDoMapper;

    /**
     * 获取一级认证申请信息(两级审核的认证编号必须一致)
     *
     * @param alumniManagerInfoVo 请求参数
     * @return 结果集
     */
    @Override
    public List<LevelOneIdentifyDo> getLevelOneIdentifyInfo(AlumniManagerInfoVo alumniManagerInfoVo) {
        LevelOneIdentifyDo levelOneIdentifyDo = new LevelOneIdentifyDo();
        levelOneIdentifyDo.setCollegeNo(alumniManagerInfoVo.getCollegeNo());
        return levelOneIdentifyDoMapper.getLevelOneIdentifyInfo(levelOneIdentifyDo);
    }

    /**
     * 获取二级认证申请信息(两级审核的认证编号必须一致)
     *
     * @param alumniManagerInfoVo 请求参数
     * @return 结果集
     */
    @Override
    public List<UserDegreeIdentifyDo> getLevelTwoIdentifyInfo(AlumniManagerInfoVo alumniManagerInfoVo) {
        UserDegreeIdentifyDo userDegreeIdentifyDo = new UserDegreeIdentifyDo();
        userDegreeIdentifyDo.setCollegeNo(alumniManagerInfoVo.getCollegeNo());
        userDegreeIdentifyDo.setInstituteNo(Long.valueOf(alumniManagerInfoVo.getInstituteNo()));
        if (TechGoalObjects.isNotEmpty(alumniManagerInfoVo.getDegreeType())) {
            userDegreeIdentifyDo.setDegreeNo(Integer.valueOf(alumniManagerInfoVo.getDegreeType()));
        }
        userDegreeIdentifyDo.setStudentName(alumniManagerInfoVo.getStudentName());
        userDegreeIdentifyDo.setGrade(alumniManagerInfoVo.getGrade());
        List<UserDegreeIdentifyDo> userDegreeIdentifyVos = userDegreeIdentifyDoMapper.getLevelTwoIdentifyInfo(userDegreeIdentifyDo);
        return userDegreeIdentifyVos;
    }

    /**
     * 获取二级认证通过的校友信息
     *
     * @param alumniManagerInfoVo 请求参数
     * @return 结果集
     */
    @Override
    public List<UserDegreeIdentifyDo> getLevelTwoIdentifiedInfo(AlumniManagerInfoVo alumniManagerInfoVo) {
        UserDegreeIdentifyDo userDegreeIdentifyDo = new UserDegreeIdentifyDo();
        userDegreeIdentifyDo.setCollegeNo(alumniManagerInfoVo.getCollegeNo());
        userDegreeIdentifyDo.setInstituteNo(Long.valueOf(alumniManagerInfoVo.getInstituteNo()));
        if (TechGoalObjects.isNotEmpty(alumniManagerInfoVo.getDegreeType())) {
            userDegreeIdentifyDo.setDegreeNo(Integer.valueOf(alumniManagerInfoVo.getDegreeType()));
        }
        userDegreeIdentifyDo.setStudentName(alumniManagerInfoVo.getStudentName());
        userDegreeIdentifyDo.setGrade(alumniManagerInfoVo.getGrade());
        return userDegreeIdentifyDoMapper.getLevelTwoIdentifiedInfo(userDegreeIdentifyDo);
    }

    /**
     * 处理学生信息展示结果
     *
     * @param list 预处理数据
     * @return 结果集
     */
    @Override
    public List<UserDegreeIdentifyVo> dealWithViewResults(List<UserDegreeIdentifyDo> list) {
        List<UserDegreeIdentifyVo> userDegreeIdentifyVos = Lists.newArrayList();
        for (UserDegreeIdentifyDo userDegreeIdentifyDo : list) {
            UserDegreeIdentifyVo userDegreeIdentifyVo = UserDegreeIdentifyConvert.toConvertVo(userDegreeIdentifyDo);
            UserJobIdentifyDo userJobIdentifyDo = new UserJobIdentifyDo();
            userJobIdentifyDo.setUserId(Long.valueOf(userDegreeIdentifyVo.getUserId()));
            userJobIdentifyDo.setEnabled(NumberDict.ONE);
            List<UserJobIdentifyDo> userJobIdentifyDos = userJobIdentifyMapper.select(userJobIdentifyDo);
            if (!CollectionUtils.isEmpty(userJobIdentifyDos)) {
                List<JobsInfoDto> jobsInfoDtos = Lists.newArrayList();
                for (UserJobIdentifyDo userJobIdentifyDo1 : userJobIdentifyDos) {
                    JobsInfoDto jobsInfoDto = new JobsInfoDto();
                    jobsInfoDto.setCompanyName(userJobIdentifyDo1.getCompanyName());
                    jobsInfoDto.setPositionName(userJobIdentifyDo1.getPositionName());
                    jobsInfoDto.setWorkStartTime(DateUtil.formatFull(userJobIdentifyDo1.getWorkStartTime()));
                    jobsInfoDto.setWorkEndTime(DateUtil.formatFull(userJobIdentifyDo1.getWorkEndTime()));
                    jobsInfoDtos.add(jobsInfoDto);
                }
                userDegreeIdentifyVo.setJobsInfoDto(jobsInfoDtos);
            }
            UserParttimeJobIdentifyDo userParttimeJobIdentifyDo = new UserParttimeJobIdentifyDo();
            userParttimeJobIdentifyDo.setUserId(Long.valueOf(userDegreeIdentifyVo.getUserId()));
            userParttimeJobIdentifyDo.setEnabled(NumberDict.ONE);
            List<UserParttimeJobIdentifyDo> userParttimeJobIdentifyDos = userParttimeJobIdentifyMapper.select(userParttimeJobIdentifyDo);
            if (!CollectionUtils.isEmpty(userParttimeJobIdentifyDos)) {
                List<PartTimeJobsDto> partTimeJobsDtos = Lists.newArrayList();
                for (UserParttimeJobIdentifyDo userParttimeJobIdentifyDo1 : userParttimeJobIdentifyDos) {
                    PartTimeJobsDto partTimeJobsDto = new PartTimeJobsDto();
                    partTimeJobsDto.setCompanyName(userParttimeJobIdentifyDo1.getCompanyName());
                    partTimeJobsDto.setPositionName(userParttimeJobIdentifyDo1.getPositionName());
                    partTimeJobsDto.setWorkStartTime(DateUtil.formatFull(userParttimeJobIdentifyDo1.getWorkStartTime()));
                    partTimeJobsDto.setWorkEndTime(DateUtil.formatFull(userParttimeJobIdentifyDo1.getWorkEndTime()));
                    partTimeJobsDtos.add(partTimeJobsDto);
                }
                userDegreeIdentifyVo.setPartTimeJobsDto(partTimeJobsDtos);
            }
            userDegreeIdentifyVos.add(userDegreeIdentifyVo);
        }
        return userDegreeIdentifyVos;
    }

    /**
     * 获取操作员信息
     *
     * @param managerId 操作员编号
     * @return 结果
     */
    @Override
    public AlumniManagerInfoDo getManagerInfo(Long managerId) {
        AlumniManagerInfoDo alumniManagerInfo = new AlumniManagerInfoDo();
        alumniManagerInfo.setUserId(managerId);
        return alumniManagerInfoDoMapper.selectOne(alumniManagerInfo);
    }

    /**
     * 修改校友认证信息
     *
     * @param alumniInfoUpdVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAlumniInfo(AlumniInfoUpdVo alumniInfoUpdVo) throws Exception {
        UserDegreeIdentifyDo userDegreeIdentifyDo = new UserDegreeIdentifyDo();
        userDegreeIdentifyDo.setIdentifyCollegeId(Long.valueOf(alumniInfoUpdVo.getIdentifyCollegeId()));
        userDegreeIdentifyDo.setInstituteNo(Long.valueOf(alumniInfoUpdVo.getInstituteNo()));
        userDegreeIdentifyDo.setEntranceTime(DateUtil.parseString2Date(alumniInfoUpdVo.getEntranceTime()));
        userDegreeIdentifyDo.setGraduationTime(DateUtil.parseString2Date(alumniInfoUpdVo.getGraduationTime()));
        if (TechGoalObjects.isNotEmpty(alumniInfoUpdVo.getDegreeType())) {
            userDegreeIdentifyDo.setDegreeNo(Integer.valueOf(alumniInfoUpdVo.getDegreeType()));
        }
        userDegreeIdentifyDo.setProfessionName(alumniInfoUpdVo.getProfessionName());
        userDegreeIdentifyDo.setIdentifyStatus(Integer.valueOf(alumniInfoUpdVo.getIdentifyStatus()));
        userDegreeIdentifyDo.setStudentNo(alumniInfoUpdVo.getStudentNo());
        userDegreeIdentifyDo.setGrade(alumniInfoUpdVo.getGrade());
        userDegreeIdentifyDo.setUpdateBy(alumniInfoUpdVo.getManagerId());
        userDegreeIdentifyDoMapper.updateAlumniInfo(userDegreeIdentifyDo);
    }

    /**
     * 修改校友信息复审
     *
     * @param alumniInfoUpdVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void levelTwoUpdateAudit(AlumniInfoUpdVo alumniInfoUpdVo) {
        UserDegreeIdentifyDo userDegreeIdentifyDo = new UserDegreeIdentifyDo();
        userDegreeIdentifyDo.setIdentifyCollegeId(Long.valueOf(alumniInfoUpdVo.getIdentifyCollegeId()));
        userDegreeIdentifyDo.setInstituteNo(Long.valueOf(alumniInfoUpdVo.getInstituteNo()));
        userDegreeIdentifyDo.setIdentifyStatus(Integer.valueOf(alumniInfoUpdVo.getIdentifyStatus()));
        userDegreeIdentifyDo.setUpdateBy(alumniInfoUpdVo.getManagerId());
        userDegreeIdentifyDoMapper.updateAlumniInfo(userDegreeIdentifyDo);
    }

    /**
     * 修改校友一级认证信息
     *
     * @param levelOneAlumniUpdInfoVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLevelOneAlumniInfo(LevelOneAlumniUpdInfoVo levelOneAlumniUpdInfoVo) throws Exception {
        LevelOneIdentifyDo levelOneIdentifyDo = new LevelOneIdentifyDo();
        levelOneIdentifyDo.setIdentifyCollegeId(Long.valueOf(levelOneAlumniUpdInfoVo.getIdentifyCollegeId()));
        levelOneIdentifyDo.setUserRealName(levelOneAlumniUpdInfoVo.getUserRealName());
        levelOneIdentifyDo.setGender(Integer.valueOf(levelOneAlumniUpdInfoVo.getGender()));
        levelOneIdentifyDo.setNation(levelOneAlumniUpdInfoVo.getNation());
        levelOneIdentifyDo.setBirthDate(DateUtil.parseString2Date(levelOneAlumniUpdInfoVo.getBirthDate()));
        levelOneIdentifyDo.setIdCard(levelOneAlumniUpdInfoVo.getIdCard());
        levelOneIdentifyDo.setOriginalIdCard(levelOneAlumniUpdInfoVo.getOriginalIdCard());
        levelOneIdentifyDo.setTrainingLevel(Integer.valueOf(levelOneAlumniUpdInfoVo.getTrainingLevel()));
        levelOneIdentifyDo.setAdmissionUnit(levelOneAlumniUpdInfoVo.getAdmissionUnit());
        levelOneIdentifyDo.setManageUnit(levelOneAlumniUpdInfoVo.getManageUnit());
        levelOneIdentifyDo.setTrainingUnit(levelOneAlumniUpdInfoVo.getTrainingUnit());
        levelOneIdentifyDo.setStudentNo(levelOneAlumniUpdInfoVo.getStudentNo());
        levelOneIdentifyDo.setNewStudentNo(levelOneAlumniUpdInfoVo.getNewStudentNo());
        levelOneIdentifyDo.setEntranceTime(DateUtil.parseString2Date(levelOneAlumniUpdInfoVo.getEntranceTime()));
        levelOneIdentifyDo.setGraduationTime(DateUtil.parseString2Date(levelOneAlumniUpdInfoVo.getGraduationTime()));
        levelOneIdentifyDo.setTutorName(levelOneAlumniUpdInfoVo.getTutorName());
        levelOneIdentifyDo.setMajorName(levelOneAlumniUpdInfoVo.getMajorName());
        levelOneIdentifyDo.setStudentStatus(levelOneAlumniUpdInfoVo.getStudentStatus());
        levelOneIdentifyDo.setJobStatus(levelOneAlumniUpdInfoVo.getJobStatus());
        levelOneIdentifyDo.setCountry(levelOneAlumniUpdInfoVo.getCountry());
        levelOneIdentifyDo.setCity(levelOneAlumniUpdInfoVo.getCity());
        levelOneIdentifyDo.setArea(levelOneAlumniUpdInfoVo.getArea());
        levelOneIdentifyDo.setEmployer(levelOneAlumniUpdInfoVo.getEmployer());
        levelOneIdentifyDo.setUnitNature(levelOneAlumniUpdInfoVo.getUnitNature());
        levelOneIdentifyDo.setExpertCategory(levelOneAlumniUpdInfoVo.getExpertCategory());
        levelOneIdentifyDo.setSpecializedTechnicalJob(levelOneAlumniUpdInfoVo.getSpecializedTechnicalJob());
        levelOneIdentifyDo.setPosition(levelOneAlumniUpdInfoVo.getPosition());
        levelOneIdentifyDo.setMailingAddress(levelOneAlumniUpdInfoVo.getMailingAddress());
        levelOneIdentifyDo.setEmail(levelOneAlumniUpdInfoVo.getEmail());
        levelOneIdentifyDo.setPhoneNo(levelOneAlumniUpdInfoVo.getPhoneNo());
        levelOneIdentifyDo.setQq(levelOneAlumniUpdInfoVo.getQq());
        levelOneIdentifyDo.setWeixinNo(levelOneAlumniUpdInfoVo.getWeixinNo());
        levelOneIdentifyDo.setUpdateBy(levelOneAlumniUpdInfoVo.getManagerId());
        levelOneIdentifyDo.setCollegeNo(levelOneAlumniUpdInfoVo.getCollegeNo());
        levelOneIdentifyDoMapper.updLevelOneIdentifyInfo(levelOneIdentifyDo);

        UserDegreeIdentifyDo userDegreeIdentifyDo = new UserDegreeIdentifyDo();
        userDegreeIdentifyDo.setIdentifyCollegeId(Long.valueOf(levelOneAlumniUpdInfoVo.getIdentifyCollegeId()));
        userDegreeIdentifyDo.setIdentifyStatus(Integer.valueOf(levelOneAlumniUpdInfoVo.getIdentifyStatus()));
        userDegreeIdentifyDo.setUpdateBy(levelOneAlumniUpdInfoVo.getManagerId());
        userDegreeIdentifyDoMapper.updateAlumniInfo(userDegreeIdentifyDo);
    }

    /**
     * 修改校友一级认证复审
     *
     * @param levelOneAlumniUpdInfoVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void levelOneUpdateAudit(LevelOneAlumniUpdInfoVo levelOneAlumniUpdInfoVo) {
        UserDegreeIdentifyDo userDegreeIdentifyDo = new UserDegreeIdentifyDo();
        userDegreeIdentifyDo.setIdentifyCollegeId(Long.valueOf(levelOneAlumniUpdInfoVo.getIdentifyCollegeId()));
        userDegreeIdentifyDo.setIdentifyStatus(Integer.valueOf(levelOneAlumniUpdInfoVo.getIdentifyStatus()));
        userDegreeIdentifyDo.setUpdateBy(levelOneAlumniUpdInfoVo.getManagerId());
        userDegreeIdentifyDoMapper.updateAlumniInfo(userDegreeIdentifyDo);
    }
}
