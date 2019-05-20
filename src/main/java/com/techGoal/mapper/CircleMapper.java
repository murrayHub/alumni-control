package com.techGoal.mapper;

import com.techGoal.pojo.dao.CircleDo;
import com.techGoal.pojo.dao.UserInfoDo;
import tk.mybatis.mapper.common.Mapper;

import javax.websocket.server.PathParam;
import java.util.List;

public interface CircleMapper extends Mapper<CircleDo> {

    /**
     * 根据圈名称模糊查询匹配圈子
     *
     * @param circleName 圈名称
     * @return 结果集
     */
    List<CircleDo> getCircleByName(@PathParam("circleName") String circleName);

    /**
     * 根据圈名称查询由用户创建的匹配圈子
     *
     * @param circleName   圈名称
     * @param circleHostNo 圈主
     * @return 结果集
     */
    List<CircleDo> getCircleByNameAndHostNo(@PathParam("circleName") String circleName, @PathParam("circleHostNo") Long circleHostNo);

    /**
     * 根据用户编号查询已加入的圈子(支持圈名模糊查询)
     *
     * @param circleDo 检索条件
     * @return 结果集
     */
    List<CircleDo> getCircleByUserId(CircleDo circleDo);

    /**
     * 获取圈成员信息
     * @param circleDo 检索条件
     * @return 结果集
     */
    List<UserInfoDo> getAllCircleMembers(CircleDo circleDo);


}