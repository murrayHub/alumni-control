package com.alumni.control.mapper;

import com.alumni.control.pojo.dao.UcasInstituteDo;
import tk.mybatis.mapper.common.Mapper;

public interface UcasInstituteDoMapper extends Mapper<UcasInstituteDo> {
    /**
     * 根据id获取学院信息
     *
     * @param id 学院编号
     * @return 结果
     */
    UcasInstituteDo getInstituteInfoById(Long id);
}