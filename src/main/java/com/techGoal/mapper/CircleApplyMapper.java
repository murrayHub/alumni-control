package com.techGoal.mapper;

import com.techGoal.pojo.dao.CircleApplyDo;
import tk.mybatis.mapper.common.Mapper;

import javax.websocket.server.PathParam;
import java.util.List;

public interface CircleApplyMapper extends Mapper<CircleApplyDo> {

    /**
     * 圈主拉取入圈申请记录
     * @param userId 用户编号
     * @return 结果集
     */
    List<CircleApplyDo> circleApplyRecord(@PathParam("userId") Long userId);

    /**
     * 圈主处理入圈申请记录
     * @param circleApplyDo 请求参数
     */
    void handleCircleApply(CircleApplyDo circleApplyDo);
}