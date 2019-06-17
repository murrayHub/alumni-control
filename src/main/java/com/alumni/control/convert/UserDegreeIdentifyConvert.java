package com.alumni.control.convert;

import com.alumni.control.enums.DegreeLevelEnum;
import com.alumni.control.enums.IdentifyStatusEnum;
import com.alumni.control.mapper.RegionMapper;
import com.alumni.control.mapper.SchoolMapper;
import com.alumni.control.mapper.UcasInstituteDoMapper;
import com.alumni.control.pojo.dao.SchoolDo;
import com.alumni.control.pojo.dao.UserDegreeIdentifyDo;
import com.alumni.control.pojo.vo.UserDegreeIdentifyVo;
import com.alumni.control.utils.DateUtil;
import com.alumni.control.utils.TechGoalObjects;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/17 13:37
 */
public class UserDegreeIdentifyConvert {

    @Autowired
    private static SchoolMapper schoolMapper;
    @Autowired
    private static RegionMapper regionMapper;
    @Autowired
    private static UcasInstituteDoMapper ucasInstituteDoMapper;

    public static UserDegreeIdentifyVo toConvertVo(UserDegreeIdentifyDo userDegreeIdentifyDo) {
        if (userDegreeIdentifyDo == null) {
            return null;
        }
        UserDegreeIdentifyVo userDegreeIdentifyVo = new UserDegreeIdentifyVo();
        userDegreeIdentifyVo.setIdentifyCollegeId(String.valueOf(userDegreeIdentifyDo.getIdentifyCollegeId()));
        userDegreeIdentifyVo.setUserId(String.valueOf(userDegreeIdentifyDo.getUserId()));
        userDegreeIdentifyVo.setCollegeNo(userDegreeIdentifyDo.getCollegeNo());
        if(TechGoalObjects.isNotEmpty(userDegreeIdentifyDo.getCollegeNo())){
            userDegreeIdentifyVo.setCollegeName(schoolMapper.getSchoolInfoById(userDegreeIdentifyDo.getCollegeNo()).getSchoolName());
        }
        userDegreeIdentifyVo.setInstituteNo(String.valueOf(userDegreeIdentifyDo.getInstituteNo()));
        if(TechGoalObjects.isNotEmpty(userDegreeIdentifyDo.getInstituteNo())){
            userDegreeIdentifyVo.setInstituteName(ucasInstituteDoMapper.getInstituteInfoById(userDegreeIdentifyDo.getInstituteNo()).getInstituteName());
        }
        userDegreeIdentifyVo.setEntranceTime(DateUtil.formatFull(userDegreeIdentifyDo.getEntranceTime()));
        userDegreeIdentifyVo.setGraduationTime(DateUtil.formatFull(userDegreeIdentifyDo.getGraduationTime()));
        userDegreeIdentifyVo.setDegreeNo(DegreeLevelEnum.getDescByCode(userDegreeIdentifyDo.getDegreeNo()));
        userDegreeIdentifyVo.setProfessionName(userDegreeIdentifyDo.getProfessionName());
        userDegreeIdentifyVo.setEnabled(String.valueOf(userDegreeIdentifyDo.getEnabled()));
        userDegreeIdentifyVo.setCreateBy(userDegreeIdentifyDo.getCreateBy());
        userDegreeIdentifyVo.setUpdateBy(userDegreeIdentifyDo.getUpdateBy());
        userDegreeIdentifyVo.setCreateAt(DateUtil.formatFull(userDegreeIdentifyDo.getCreateAt()));
        userDegreeIdentifyVo.setUpdateAt(DateUtil.formatFull(userDegreeIdentifyDo.getUpdateAt()));
        userDegreeIdentifyVo.setIdentifyStatus(String.valueOf(userDegreeIdentifyDo.getIdentifyStatus()));
        userDegreeIdentifyVo.setIdentifyStatusStr(IdentifyStatusEnum.getDescByCode(userDegreeIdentifyDo.getIdentifyStatus()));
        userDegreeIdentifyVo.setStudentNo(userDegreeIdentifyDo.getStudentNo());
        userDegreeIdentifyVo.setIdCardNo(userDegreeIdentifyDo.getIdCardNo());
        userDegreeIdentifyVo.setIdentifyType(String.valueOf(userDegreeIdentifyDo.getIdentifyType()));
        userDegreeIdentifyVo.setGrade(userDegreeIdentifyDo.getGrade());
        userDegreeIdentifyVo.setStudentName(userDegreeIdentifyDo.getStudentName());
        userDegreeIdentifyVo.setGender(String.valueOf(userDegreeIdentifyDo.getGender()));
        if(TechGoalObjects.isNotEmpty(userDegreeIdentifyDo.getProvince())){
            userDegreeIdentifyVo.setProvince(regionMapper.selectAreaById(userDegreeIdentifyDo.getProvince()).getSname());
        }
        if(TechGoalObjects.isNotEmpty(userDegreeIdentifyDo.getCity())){
            userDegreeIdentifyVo.setCity(regionMapper.selectAreaById(userDegreeIdentifyDo.getCity()).getSname());
        }
        return userDegreeIdentifyVo;
    }
}
