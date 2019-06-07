package com.techGoal.service;

import com.techGoal.pojo.dto.PersonalMomentsDetailDto;
import com.techGoal.pojo.dto.PersonalMomentsDto;
import com.techGoal.pojo.vo.MomentsVo;

import java.util.List;

/**
 * description : 朋友圈动态管理-服务层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/30 10:54
 */
public interface MomentsManageService {
    /**
     * 个人-朋友圈动态-新增
     * @param momentsVo 动态内容
     */
    void createPerMoments(MomentsVo momentsVo);

    /**
     * 圈子-朋友圈动态-新增
     * @param momentsVo 动态内容
     */
    void createCircleMoments(MomentsVo momentsVo);

    /**
     * 获取发现-所有动态
     * @param userId 用户编号
     * @return 结果集
     */
    List<MomentsVo> getDiscoverMoments(Long userId);

    /**
     * 获取圈内-所有动态
     * @param circleNo 圈子编号
     * @return 结果集
     */
    List<MomentsVo> getCircleMoments(Long circleNo);

    /**
     * 获取个人-所有动态
     * @param userId 用户编号
     * @return 结果集
     */
    List<PersonalMomentsDto> getPersonalMoments(Long userId);

    /**
     * 朋友圈动态-动态详情-查询
     * @param momentsId 动态编号
     * @return 结果集
     */
    PersonalMomentsDetailDto getPersonalMomentDetail(Long momentsId);

}
