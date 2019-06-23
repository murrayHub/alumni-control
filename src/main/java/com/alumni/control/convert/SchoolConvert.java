package com.alumni.control.convert;

import com.alumni.control.pojo.dao.SchoolDo;
import com.alumni.control.pojo.dao.UcasInstituteDo;
import com.alumni.control.pojo.vo.SchoolVo;
import com.alumni.control.pojo.vo.UcasInstituteVo;
import com.alumni.control.utils.DateUtil;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/23 21:15
 */
public class SchoolConvert {

    public static SchoolVo toConvert(SchoolDo schoolDo) {
        if (schoolDo == null) {
            return null;
        }
        SchoolVo schoolVo = new SchoolVo();
        schoolVo.setSchoolId(schoolDo.getSchoolId());
        schoolVo.setSchoolName(schoolDo.getSchoolName());
        schoolVo.setProvinceId(schoolDo.getProvinceId());
        schoolVo.setProvinceName(schoolDo.getProvinceName());
        schoolVo.setCityId(schoolDo.getCityId());
        schoolVo.setCityName(schoolDo.getCityName());
        schoolVo.setLevel(schoolDo.getLevel());
        schoolVo.setDepartment(schoolDo.getDepartment());
        schoolVo.setOther(schoolDo.getOther());
        return schoolVo;
    }

    public static UcasInstituteVo toConvert(UcasInstituteDo ucasInstituteDo) {
        if (ucasInstituteDo == null) {
            return null;
        }
        UcasInstituteVo ucasInstituteVo = new UcasInstituteVo();
        ucasInstituteVo.setInstituteNo(String.valueOf(ucasInstituteDo.getInstituteNo()));
        ucasInstituteVo.setInstituteName(ucasInstituteDo.getInstituteName());
        ucasInstituteVo.setEnabled(String.valueOf(ucasInstituteDo.getEnabled()));
        ucasInstituteVo.setCreateBy(ucasInstituteDo.getCreateBy());
        ucasInstituteVo.setUpdateBy(ucasInstituteDo.getUpdateBy());
        ucasInstituteVo.setCreateAt(DateUtil.formatSmall(ucasInstituteDo.getCreateAt()));
        ucasInstituteVo.setUpdateAt(DateUtil.formatSmall(ucasInstituteDo.getUpdateAt()));
        ucasInstituteVo.setLocation(ucasInstituteDo.getLocation());
        return ucasInstituteVo;
    }
}
