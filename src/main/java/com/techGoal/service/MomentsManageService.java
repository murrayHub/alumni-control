package com.techGoal.service;

import com.techGoal.pojo.vo.MomentsVo;

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

}
