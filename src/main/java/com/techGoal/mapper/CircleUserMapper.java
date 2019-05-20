package com.techGoal.mapper;

import com.techGoal.pojo.dao.CircleUserDo;
import com.techGoal.pojo.vo.ExitCircleVo;
import tk.mybatis.mapper.common.Mapper;

public interface CircleUserMapper extends Mapper<CircleUserDo> {
    /**
     * 退出圈子
     * @param CircleUserDo 请求参数
     */
    void exitCircle(CircleUserDo CircleUserDo);
}