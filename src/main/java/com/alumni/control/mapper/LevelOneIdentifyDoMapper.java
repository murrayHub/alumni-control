package com.alumni.control.mapper;

import com.alumni.control.pojo.dao.LevelOneIdentifyDo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface LevelOneIdentifyDoMapper extends Mapper<LevelOneIdentifyDo> {
    /**
     * 获取一级认证申请信息
     * @param levelOneIdentifyDo 请求参数
     * @return 结果集
     */
    List<LevelOneIdentifyDo> getLevelOneIdentifyInfo(LevelOneIdentifyDo levelOneIdentifyDo);

    /**
     * 修改一级认证申请信息
     * @param levelOneIdentifyDo 请求参数
     */
    void updLevelOneIdentifyInfo(LevelOneIdentifyDo levelOneIdentifyDo);

}