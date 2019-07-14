package com.alumni.control.convert;

import com.alumni.control.enums.DegreeLevelEnum;
import com.alumni.control.enums.IdentifyStatusEnum;
import com.alumni.control.pojo.dao.UserDegreeIdentifyDo;
import com.alumni.control.pojo.vo.UserDegreeIdentifyVo;
import com.alumni.control.utils.DateUtil;

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

    public static UserDegreeIdentifyVo toConvertVo(UserDegreeIdentifyDo userDegreeIdentifyDo) throws Exception {
        if (userDegreeIdentifyDo == null) {
            return null;
        }
        UserDegreeIdentifyVo userDegreeIdentifyVo = new UserDegreeIdentifyVo();
        userDegreeIdentifyVo.setIdentifyCollegeId(String.valueOf(userDegreeIdentifyDo.getIdentifyCollegeId()));
        userDegreeIdentifyVo.setUserId(String.valueOf(userDegreeIdentifyDo.getUserId()));
        userDegreeIdentifyVo.setCollegeNo(userDegreeIdentifyDo.getCollegeNo());
        userDegreeIdentifyVo.setInstituteNo(String.valueOf(userDegreeIdentifyDo.getInstituteNo()));
        userDegreeIdentifyVo.setEntranceTime(DateUtil.formatSmall(userDegreeIdentifyDo.getEntranceTime()));
        userDegreeIdentifyVo.setGraduationTime(DateUtil.formatSmall(userDegreeIdentifyDo.getGraduationTime()));
        userDegreeIdentifyVo.setDegreeName(DegreeLevelEnum.getDescByCode(userDegreeIdentifyDo.getDegreeNo()));
        userDegreeIdentifyVo.setDegreeNo(String.valueOf(userDegreeIdentifyDo.getDegreeNo()));
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
        userDegreeIdentifyVo.setIdentifyTypeValue(userDegreeIdentifyDo.getIdentifyType() == 1 ? "身份证号" : "学号");
        String gradeStr = userDegreeIdentifyDo.getGrade().substring(0,4);
        userDegreeIdentifyVo.setGrade(gradeStr);
        userDegreeIdentifyVo.setStudentName(userDegreeIdentifyDo.getStudentName());
        userDegreeIdentifyVo.setGender(String.valueOf(userDegreeIdentifyDo.getGender()));
        userDegreeIdentifyVo.setGenderValue(userDegreeIdentifyDo.getGender() == 1 ? "男":"女" );
        return userDegreeIdentifyVo;
    }
}
