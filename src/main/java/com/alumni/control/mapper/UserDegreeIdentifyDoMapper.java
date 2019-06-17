package com.alumni.control.mapper;

import com.alumni.control.pojo.dao.UserDegreeIdentifyDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDegreeIdentifyDoMapper extends Mapper<UserDegreeIdentifyDo> {


    /**
     * 获取二级认证申请信息
     * @param userDegreeIdentifyDo 请求参数
     * @return 结果集
     */
    List<UserDegreeIdentifyDo> getLevelTwoIdentifyInfo(UserDegreeIdentifyDo userDegreeIdentifyDo);

    /**
     * 获取二级认证通过的校友信息
     * @param userDegreeIdentifyDo 请求参数
     * @return 结果集
     */
    List<UserDegreeIdentifyDo> getLevelTwoIdentifiedInfo(UserDegreeIdentifyDo userDegreeIdentifyDo);

    /**
     * 修改校友认证信息
     * @param userDegreeIdentifyDo 请求参数
     */
    void updateAlumniInfo(UserDegreeIdentifyDo userDegreeIdentifyDo);
}