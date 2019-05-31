package com.techGoal.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.techGoal.constant.RedisDict;
import com.techGoal.convert.MomentsConvert;
import com.techGoal.dict.NumberDict;
import com.techGoal.mapper.AttentionRelationMapper;
import com.techGoal.mapper.CircleMomentsMapper;
import com.techGoal.mapper.MomentsTimeAxisMapper;
import com.techGoal.mapper.PersonalMomentsMapper;
import com.techGoal.pojo.dao.*;
import com.techGoal.pojo.vo.MomentsVo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.redis.OrderIdManager;
import com.techGoal.redis.RedisUtil;
import com.techGoal.service.CircleManageService;
import com.techGoal.service.MomentsManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * description : 朋友圈动态管理-服务层-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/30 10:55
 */
@Slf4j
@Service
public class MomentsManageServiceImpl implements MomentsManageService {

    /**
     * 流水号创建服务
     */
    @Autowired
    private OrderIdManager orderIdManager;
    /**
     * 圈子管理-服务层
     */
    @Autowired
    private CircleManageService circleManageService;
    /**
     * 个人动态发布 Mapper
     */
    @Autowired
    private PersonalMomentsMapper personalMomentsMapper;
    /**
     * 动态时间轴 Mapper
     */
    @Autowired
    private MomentsTimeAxisMapper momentsTimeAxisMapper;
    /**
     * 圈子动态发布 Mapper
     */
    @Autowired
    private CircleMomentsMapper circleMomentsMapper;
    /**
     * 关注和被关注关系管理 Mapper
     */
    @Autowired
    private AttentionRelationMapper attentionRelationMapper;

    /**
     * 缓存类接口
     */
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 朋友圈动态-新增
     *
     * @param momentsVo 动态内容
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createPerMoments(MomentsVo momentsVo) {
        PersonalMomentsDo personalMomentsDo = MomentsConvert.convertToPerMomentsDo(momentsVo);
        personalMomentsDo.setStatus(NumberDict.ONE);
        personalMomentsDo.setPublisherTime(new Date());
        Long applyCircleId = orderIdManager.orderIdCreate(RedisDict.MOMENTS_NO_KEY);
        personalMomentsDo.setMomentsId(applyCircleId);
        personalMomentsMapper.insert(personalMomentsDo);
        // 获取粉丝集合 <Set>
        Set<String> followerList = this.getFollowersList(momentsVo.getPublisherId());
        // 将动态插入朋友圈时间轴
        for (String follower : followerList) {
            // 遍历插入动态记录
            MomentsTimeAxisDo momentsTimeAxisDo = new MomentsTimeAxisDo();
            momentsTimeAxisDo.setMomentsId(applyCircleId);
            momentsTimeAxisDo.setUserId(Long.valueOf(follower));
            momentsTimeAxisDo.setIsOwn(NumberDict.ZERO);
            momentsTimeAxisDo.setCreateTime(new Date());
            momentsTimeAxisDo.setCreateBy(follower);
            momentsTimeAxisMapper.insert(momentsTimeAxisDo);
        }
        // 插入自己的记录
        MomentsTimeAxisDo momentsTimeAxisDo = new MomentsTimeAxisDo();
        momentsTimeAxisDo.setMomentsId(applyCircleId);
        momentsTimeAxisDo.setUserId(Long.valueOf(momentsVo.getPublisherId()));
        momentsTimeAxisDo.setIsOwn(NumberDict.ONE);
        momentsTimeAxisDo.setCreateTime(new Date());
        momentsTimeAxisDo.setCreateBy(momentsVo.getPublisherId());
        momentsTimeAxisMapper.insert(momentsTimeAxisDo);
    }

    /**
     * 圈子-朋友圈动态-新增
     *
     * @param momentsVo 动态内容
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createCircleMoments(MomentsVo momentsVo) {
        CircleMomentsDo circleMomentsDo = MomentsConvert.convertToCirMomentsDo(momentsVo);
        circleMomentsDo.setStatus(NumberDict.ONE);
        circleMomentsDo.setPublisherTime(new Date());
        Long applyCircleId = orderIdManager.orderIdCreate(RedisDict.MOMENTS_NO_KEY);
        circleMomentsDo.setMomentsId(applyCircleId);
        circleMomentsMapper.insert(circleMomentsDo);
        // 获取圈内人集合 <Set>
        Set<String> insiderList = this.getInsiders(momentsVo.getCircleNo());
        // 将动态插入朋友圈时间轴
        for (String insider : insiderList) {
            // 遍历插入时间轴动态记录
            MomentsTimeAxisDo momentsTimeAxisDo = new MomentsTimeAxisDo();
            momentsTimeAxisDo.setMomentsId(applyCircleId);
            momentsTimeAxisDo.setCircleNo(Long.valueOf(momentsVo.getCircleNo()));
            momentsTimeAxisDo.setUserId(Long.valueOf(insider));
            momentsTimeAxisDo.setIsOwn(NumberDict.ZERO);
            momentsTimeAxisDo.setCreateTime(new Date());
            momentsTimeAxisDo.setCreateBy(insider);
            momentsTimeAxisMapper.insert(momentsTimeAxisDo);
        }
        // 插入自己的时间轴记录
        MomentsTimeAxisDo momentsTimeAxisDo = new MomentsTimeAxisDo();
        momentsTimeAxisDo.setMomentsId(applyCircleId);
        momentsTimeAxisDo.setCircleNo(Long.valueOf(momentsVo.getCircleNo()));
        momentsTimeAxisDo.setUserId(Long.valueOf(momentsVo.getPublisherId()));
        momentsTimeAxisDo.setIsOwn(NumberDict.ONE);
        momentsTimeAxisDo.setCreateTime(new Date());
        momentsTimeAxisDo.setCreateBy(momentsVo.getPublisherId());
        momentsTimeAxisMapper.insert(momentsTimeAxisDo);
    }

    /**
     * 获取发现-所有动态
     *
     * @param userId 用户编号
     * @return 结果集
     */
    @Override
    public List<MomentsVo> getDiscoverMoments(Long userId) {
        List<MomentsVo> momentsVos = Lists.newArrayList();
        MomentsTimeAxisDo momentsTimeAxisDo = new MomentsTimeAxisDo();
        momentsTimeAxisDo.setUserId(userId);
        List<MomentsTimeAxisDo> momentsTimeAxisDos = momentsTimeAxisMapper.queryDiscoverMoments(momentsTimeAxisDo);
        for(MomentsTimeAxisDo momentsTimeAxis : momentsTimeAxisDos){
            if(momentsTimeAxis.getCircleNo() == NumberDict.ZERO){
                // 非圈内动态
                PersonalMomentsDo personalMomentsDo = new PersonalMomentsDo();
                personalMomentsDo.setMomentsId(momentsTimeAxis.getMomentsId());
                List<PersonalMomentsDo> personalMomentsDos = personalMomentsMapper.queryPersonalMoments(personalMomentsDo);
                // 动态绑定的回复留言获取
                // 动态绑定的点赞获取
            }else {
                // 圈内动态
                CircleMomentsDo circleMomentsDo = new CircleMomentsDo();
                circleMomentsDo.setMomentsId(momentsTimeAxis.getMomentsId());
                circleMomentsDo.setCircleNo(momentsTimeAxis.getCircleNo());
                List<CircleMomentsDo> circleMomentsDos = circleMomentsMapper.queryCircleMoments(circleMomentsDo);
                // 动态绑定的回复留言获取
                // 动态绑定的点赞获取
            }
        }
        return momentsVos;
    }


    /**
     * 获取圈内-所有动态
     *
     * @param circleNo 圈子编号
     * @return 结果集
     */
    @Override
    public List<MomentsVo> getCircleMoments(Long circleNo) {
        return null;
    }

    /**
     * 获取个人-所有动态
     *
     * @param userId 用户编号
     * @return 结果集
     */
    @Override
    public List<MomentsVo> getPersonalMoments(Long userId) {
        return null;
    }

    /**
     * 获取粉丝集合
     *
     * @param userId 用户编号
     * @return 结果集
     */
    public Set<String> getFollowersList(String userId) {
        Set<String> result = Sets.newConcurrentHashSet();
        // 先从缓存中获取
        Set<String> followerList = redisUtil.sMembers(RedisDict.FOLLOWERS_KEY + userId);
        if (!CollectionUtils.isEmpty(followerList)) {
            return followerList;
        }
        // 从数据库获取
        AttentionRelationDo attentionRelationDo = new AttentionRelationDo();
        attentionRelationDo.setUserId(Long.valueOf(userId));
        List<UserInfoDo> userInfoVoList = attentionRelationMapper.getBePayedAttentionUsers(attentionRelationDo);
        for (UserInfoDo userInfoDo : userInfoVoList) {
            result.add(String.valueOf(userInfoDo.getUserId()));
            // 数据库存在数据，则更新缓存记录
            redisUtil.sAdd(RedisDict.FOLLOWERS_KEY + userId, String.valueOf(userInfoDo.getUserId()));
        }
        return result;

    }


    /**
     * 获取圈内人集合
     *
     * @param circleNo 圈子编号
     * @return 结果集
     */
    public Set<String> getInsiders(String circleNo) {
        Set<String> result = Sets.newConcurrentHashSet();
        // 先从缓存中获取
        Set<String> insidersList = redisUtil.sMembers(RedisDict.INSIDERS_KEY + circleNo);
        if (!CollectionUtils.isEmpty(insidersList)) {
            return insidersList;
        }
        List<UserInfoVo> userInfoVoList = circleManageService.getAllCircleMembers(Long.valueOf(circleNo));
        for (UserInfoVo userInfoVo : userInfoVoList) {
            result.add(String.valueOf(userInfoVo.getUserId()));
            // 数据库存在数据，则更新缓存记录
            redisUtil.sAdd(RedisDict.INSIDERS_KEY + circleNo, String.valueOf(userInfoVo.getUserId()));
        }
        return result;
    }
}
