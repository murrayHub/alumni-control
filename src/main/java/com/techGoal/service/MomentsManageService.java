package com.techGoal.service;

import com.techGoal.pojo.dto.MomentsDetailDto;
import com.techGoal.pojo.dto.PersonalMomentsDto;
import com.techGoal.pojo.vo.CirclePraiseVo;
import com.techGoal.pojo.vo.CommentVo;
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
    List<MomentsDetailDto> getDiscoverMoments(Long userId);

    /**
     * 获取圈内-所有动态
     * @param circleNo 圈子编号
     * @return 结果集
     */
    List<MomentsDetailDto> getCircleMoments(Long circleNo);

    /**
     * 获取个人-所有动态
     * @param userId 用户编号
     * @return 结果集
     */
    List<PersonalMomentsDto> getPersonalMoments(Long userId);

    /**
     * 朋友圈-个人动态-动态详情-查询
     * @param momentsId 动态编号
     * @return 结果集
     */
    MomentsDetailDto getPersonalMomentDetail(Long momentsId);
    /**
     * 朋友圈-圈内动态-动态详情-查询
     * @param momentsId 动态编号
     * @param circleNo 圈子编号
     * @return 结果集
     */
    MomentsDetailDto getCircleMomentDetail(Long momentsId, Long circleNo);

    /**
     * 朋友圈动态-添加评论
     * @param commentVo 评论内容
     */
    void addComments(CommentVo commentVo);

    /**
     * 朋友圈动态-点赞
     * @param circlePraiseVo 请求参数
     */
    void addThumbUp(CirclePraiseVo circlePraiseVo);

}
