package com.alumni.control.mapper;

import com.alumni.control.pojo.dao.SchoolDo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SchoolMapper extends Mapper<SchoolDo> {
    /**
     * 根据id获取学校信息
     *
     * @param id 学校编号
     * @return 结果
     */
    SchoolDo getSchoolInfoById(@Param("id") String id);
    /**
     * 获取所有学校信息
     *
     * @return 结果集
     */
    List<SchoolDo> getSchools();
}