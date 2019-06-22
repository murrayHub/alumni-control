package com.alumni.control.convert;

import com.alumni.control.enums.DegreeLevelEnum;
import com.alumni.control.enums.IdentifyStatusEnum;
import com.alumni.control.pojo.dao.LevelOneIdentifyDo;
import com.alumni.control.pojo.vo.LevelOneIdentifyVo;
import com.alumni.control.utils.DateUtil;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 19:52
 */
public class LevelOneIdentifyConvert {
    public static LevelOneIdentifyVo toConvertVo(LevelOneIdentifyDo levelOneIdentifyDo) {
        if (levelOneIdentifyDo == null) {
            return null;
        }
        LevelOneIdentifyVo levelOneIdentifyVo = new LevelOneIdentifyVo();
        levelOneIdentifyVo.setIdentifyCollegeId(String.valueOf(levelOneIdentifyDo.getIdentifyCollegeId()));
        levelOneIdentifyVo.setUserRealName(levelOneIdentifyDo.getUserRealName());
        levelOneIdentifyVo.setGender(String.valueOf(levelOneIdentifyDo.getGender()));
        levelOneIdentifyVo.setGenderValue(levelOneIdentifyDo.getGender()==1?"男":"女");
        levelOneIdentifyVo.setNation(levelOneIdentifyDo.getNation());
        levelOneIdentifyVo.setBirthDate(DateUtil.formatSmall(levelOneIdentifyDo.getBirthDate()));
        levelOneIdentifyVo.setIdCard(levelOneIdentifyDo.getIdCard());
        levelOneIdentifyVo.setOriginalIdCard(levelOneIdentifyDo.getOriginalIdCard());
        levelOneIdentifyVo.setTrainingLevel(DegreeLevelEnum.getDescByCode(levelOneIdentifyDo.getTrainingLevel()));
        levelOneIdentifyVo.setAdmissionUnit(levelOneIdentifyDo.getAdmissionUnit());
        levelOneIdentifyVo.setManageUnit(levelOneIdentifyDo.getManageUnit());
        levelOneIdentifyVo.setTrainingUnit(levelOneIdentifyDo.getTrainingUnit());
        levelOneIdentifyVo.setStudentNo(levelOneIdentifyDo.getStudentNo());
        levelOneIdentifyVo.setNewStudentNo(levelOneIdentifyDo.getNewStudentNo());
        levelOneIdentifyVo.setEntranceTime(DateUtil.formatSmall(levelOneIdentifyDo.getEntranceTime()));
        levelOneIdentifyVo.setGraduationTime(DateUtil.formatSmall(levelOneIdentifyDo.getGraduationTime()));
        levelOneIdentifyVo.setTutorName(levelOneIdentifyDo.getTutorName());
        levelOneIdentifyVo.setMajorName(levelOneIdentifyDo.getMajorName());
        levelOneIdentifyVo.setStudentStatus(levelOneIdentifyDo.getStudentStatus());
        levelOneIdentifyVo.setJobStatus(levelOneIdentifyDo.getJobStatus());
        levelOneIdentifyVo.setCountry(levelOneIdentifyDo.getCountry());
        levelOneIdentifyVo.setCity(levelOneIdentifyDo.getCity());
        levelOneIdentifyVo.setArea(levelOneIdentifyDo.getArea());
        levelOneIdentifyVo.setEmployer(levelOneIdentifyDo.getEmployer());
        levelOneIdentifyVo.setUnitNature(levelOneIdentifyDo.getUnitNature());
        levelOneIdentifyVo.setExpertCategory(levelOneIdentifyDo.getExpertCategory());
        levelOneIdentifyVo.setSpecializedTechnicalJob(levelOneIdentifyDo.getSpecializedTechnicalJob());
        levelOneIdentifyVo.setPosition(levelOneIdentifyDo.getPosition());
        levelOneIdentifyVo.setMailingAddress(levelOneIdentifyDo.getMailingAddress());
        levelOneIdentifyVo.setEmail(levelOneIdentifyDo.getEmail());
        levelOneIdentifyVo.setPhoneNo(levelOneIdentifyDo.getPhoneNo());
        levelOneIdentifyVo.setQq(levelOneIdentifyDo.getQq());
        levelOneIdentifyVo.setWeixinNo(levelOneIdentifyDo.getWeixinNo());
        levelOneIdentifyVo.setCreateBy(levelOneIdentifyDo.getCreateBy());
        levelOneIdentifyVo.setUpdateBy(levelOneIdentifyDo.getUpdateBy());
        levelOneIdentifyVo.setCreateAt(DateUtil.formatFull(levelOneIdentifyDo.getCreateAt()));
        levelOneIdentifyVo.setUpdateAt(DateUtil.formatFull(levelOneIdentifyDo.getUpdateAt()));
        levelOneIdentifyVo.setCollegeNo(levelOneIdentifyDo.getCollegeNo());
        levelOneIdentifyVo.setIdentifyStatus(String.valueOf(levelOneIdentifyDo.getIdentifyStatus()));
        levelOneIdentifyVo.setIdentifyStatusStr(IdentifyStatusEnum.getDescByCode(levelOneIdentifyDo.getIdentifyStatus()));
        return levelOneIdentifyVo;
    }
}
