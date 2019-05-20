package com.techGoal.service;

import com.techGoal.pojo.bo.CircleBo;
import com.techGoal.pojo.vo.CircleApplyVo;
import com.techGoal.pojo.vo.ExitCircleVo;
import com.techGoal.pojo.vo.HandleCircleApplyVo;
import com.techGoal.pojo.vo.UserInfoVo;

import java.util.List;

/**
 * description : 圈子管理-服务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 10:15
 */
public interface CircleManageService {

    /**
     * 根据圈名称查询匹配圈子
     *
     * @param circleName 圈名称
     * @return 结果集
     */
    List<CircleBo> getCircleByName(String circleName);

    /**
     * 根据圈名称查询由用户创建的匹配圈子
     * @param circleName 圈名称
     * @param circleHostNo 圈主
     * @return 结果集
     */
    List<CircleBo> getCircleByNameAndHostNo(String circleName,String circleHostNo);

    /**
     * 根据用户编号查询已加入的圈子(支持圈名模糊查询)
     *
     * @param circleBo 检索条件
     * @return 结果集
     */
    List<CircleBo> getCircleByUserId(CircleBo circleBo);

    /**
     * 根据圈子编号查询圈子主页信息
     *
     * @param circleNo 圈编号
     * @return 结果集
     */
    CircleBo getCircleInfoByCircleNo(String circleNo);

    /**
     * 获取圈成员信息
     * @param circleNo 圈编号
     * @return 结果集
     */
    List<UserInfoVo> getAllCircleMembers(Long circleNo);

    /**
     * 入圈申请
     * @param circleApplyVo 请求参数
     */
    void circleApply(CircleApplyVo circleApplyVo);

    /**
     * 圈主拉取入圈申请记录
     * @param userId 用户编号
     * @return 结果集
     */
    List<CircleApplyVo> circleApplyRecord(Long userId);
    /**
     * 拉取个人入圈申请记录
     * @param userId 用户编号
     * @return 结果集
     */
    List<CircleApplyVo> circleApplyPerRecord(Long userId);

    /**
     * 退出圈子
     * @param exitCircleVo 请求参数
     */
    void exitCircle(ExitCircleVo exitCircleVo);

    /**
     * 圈主处理入圈申请记录
     * @param handleCircleApplyVo 请求参数
     */
    void handleCircleApply(HandleCircleApplyVo handleCircleApplyVo);
}
