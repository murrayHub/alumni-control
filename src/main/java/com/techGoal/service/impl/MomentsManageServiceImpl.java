package com.techGoal.service.impl;

import com.techGoal.constant.RedisDict;
import com.techGoal.convert.MomentsConvert;
import com.techGoal.dict.NumberDict;
import com.techGoal.mapper.CircleMomentsMapper;
import com.techGoal.mapper.MomentsTimeAxisMapper;
import com.techGoal.mapper.PersonalMomentsMapper;
import com.techGoal.pojo.dao.CircleMomentsDo;
import com.techGoal.pojo.dao.MomentsTimeAxisDo;
import com.techGoal.pojo.dao.PersonalMomentsDo;
import com.techGoal.pojo.vo.MomentsVo;
import com.techGoal.redis.OrderIdManager;
import com.techGoal.redis.RedisUtil;
import com.techGoal.service.MomentsManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        Set<String> followerList =  redisUtil.sMembers(RedisDict.FOLLOWERS_KEY + momentsVo.getPublisherId());
        // 将动态插入朋友圈时间轴
        for(String follower : followerList){
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
        Set<String> insiderList =  redisUtil.sMembers(RedisDict.INSIDERS_KEY + momentsVo.getCircleNo());
        // 将动态插入朋友圈时间轴
        for(String insider : insiderList){
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
}
