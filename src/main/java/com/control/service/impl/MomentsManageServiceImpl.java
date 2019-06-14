package com.control.service.impl;

import com.control.constant.RedisDict;
import com.control.dict.NumberDict;
import com.control.mapper.*;
import com.control.pojo.dao.*;
import com.control.pojo.dto.CommentDto;
import com.control.pojo.dto.MomentsDetailDto;
import com.control.pojo.dto.PersonalMomentsDto;
import com.control.pojo.vo.CirclePraiseVo;
import com.control.pojo.vo.CommentVo;
import com.control.pojo.vo.MomentsVo;
import com.control.pojo.vo.UserInfoVo;
import com.control.redis.OrderIdManager;
import com.control.redis.RedisUtil;
import com.control.service.CircleManageService;
import com.control.service.MomentsManageService;
import com.control.utils.TechGoalObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.techGoal.constant.RedisDict;
import com.techGoal.convert.CircleMomentsConvert;
import com.techGoal.convert.CommentConvert;
import com.techGoal.convert.MomentsConvert;
import com.techGoal.convert.PersonalMomentsConvert;
import com.techGoal.dict.NumberDict;
import com.techGoal.mapper.*;
import com.techGoal.pojo.dao.*;
import com.techGoal.pojo.dto.CommentDto;
import com.techGoal.pojo.dto.MomentsDetailDto;
import com.techGoal.pojo.dto.PersonalMomentsDto;
import com.techGoal.pojo.vo.CirclePraiseVo;
import com.techGoal.pojo.vo.CommentVo;
import com.techGoal.pojo.vo.MomentsVo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.redis.OrderIdManager;
import com.techGoal.redis.RedisUtil;
import com.techGoal.service.CircleManageService;
import com.techGoal.service.MomentsManageService;
import com.techGoal.utils.TechGoalObjects;
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
     * 用户信息 Mapper
     */
    @Autowired
    private UserInfoMapper userInfoMapper;
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
     * 朋友圈评论 Mapper
     */
    @Autowired
    private CommentDoMapper commentDoMapper;

    /**
     * 朋友圈点赞 Mapper
     */
    @Autowired
    private CirclePraiseMapper circlePraiseMapper;
    /**
     * 圈子 Mapper
     */
    @Autowired
    private CircleMapper circleMapper;

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
    public List<MomentsDetailDto> getDiscoverMoments(Long userId) {
        List<MomentsDetailDto> momentsDetailDtos = Lists.newArrayList();
        MomentsTimeAxisDo momentsTimeAxisDo = new MomentsTimeAxisDo();
        momentsTimeAxisDo.setUserId(userId);
        List<MomentsTimeAxisDo> momentsTimeAxisDos = momentsTimeAxisMapper.queryDiscoverMoments(momentsTimeAxisDo);
        for (MomentsTimeAxisDo momentsTimeAxis : momentsTimeAxisDos) {
            if (momentsTimeAxis.getCircleNo() == NumberDict.ZERO) {
                // 非圈内动态
                PersonalMomentsDo personalMomentsDo = new PersonalMomentsDo();
                personalMomentsDo.setMomentsId(momentsTimeAxis.getMomentsId());
                List<PersonalMomentsDo> personalMomentsDos = personalMomentsMapper.queryPersonalMoments(personalMomentsDo);
                if(!CollectionUtils.isEmpty(personalMomentsDos)){
                    // 动态的回复留言统计获取
                    PersonalMomentsDo personalMomentsDo1 = personalMomentsDos.get(0);
                    CommentDo commentDo = new CommentDo();
                    commentDo.setTopicId(personalMomentsDo1.getMomentsId());
                    commentDo.setTopicType(NumberDict.ONE);
                    List<CommentDo> commentDos = commentDoMapper.getAllComments(commentDo);
                    personalMomentsDo1.setCommentsCount(commentDos.size());
                    // 动态绑定的点赞统计获取
                    CirclePraiseDo circlePraiseDo = new CirclePraiseDo();
                    circlePraiseDo.setMomentsId(personalMomentsDo1.getMomentsId());
                    circlePraiseDo.setPraiseType(NumberDict.ONE);
                    List<CirclePraiseDo> circlePraiseDos = circlePraiseMapper.getAllPraises(circlePraiseDo);
                    personalMomentsDo1.setPraiseCount(circlePraiseDos.size());
                    MomentsDetailDto momentsDetailDto = PersonalMomentsConvert.convertToDetailDto(personalMomentsDo1);
                    momentsDetailDtos.add(momentsDetailDto);
                }
            } else {
                // 圈内动态
                CircleMomentsDo circleMomentsDo = new CircleMomentsDo();
                circleMomentsDo.setMomentsId(momentsTimeAxis.getMomentsId());
                circleMomentsDo.setCircleNo(momentsTimeAxis.getCircleNo());
                List<CircleMomentsDo> circleMomentsDos = circleMomentsMapper.queryCircleMoments(circleMomentsDo);
                if(!CollectionUtils.isEmpty(circleMomentsDos)){
                    CircleMomentsDo circleMomentsDo1 = circleMomentsDos.get(0);
                    // 动态的回复留言统计获取
                    CommentDo commentDo = new CommentDo();
                    commentDo.setTopicId(circleMomentsDo1.getMomentsId());
                    commentDo.setTopicType(NumberDict.ONE);
                    List<CommentDo> commentDos = commentDoMapper.getAllComments(commentDo);
                    circleMomentsDo1.setCommentsCount(commentDos.size());
                    // 动态绑定的点赞统计获取
                    CirclePraiseDo circlePraiseDo = new CirclePraiseDo();
                    circlePraiseDo.setMomentsId(circleMomentsDo1.getMomentsId());
                    circlePraiseDo.setPraiseType(NumberDict.ONE);
                    List<CirclePraiseDo> circlePraiseDos = circlePraiseMapper.getAllPraises(circlePraiseDo);
                    circleMomentsDo1.setPraiseCount(circlePraiseDos.size());
                    MomentsDetailDto momentsDetailDto = CircleMomentsConvert.convertToDetailDto(circleMomentsDo1);
                    CircleDo circleDo = new CircleDo();
                    circleDo.setCircleNo(Long.valueOf(momentsDetailDto.getCircleNo()));
                    CircleDo circleDo1 = circleMapper.selectOne(circleDo);
                    momentsDetailDto.setCircleName(circleDo1.getCircleName());
                    momentsDetailDtos.add(momentsDetailDto);
                }
            }
        }
        return momentsDetailDtos;
    }


    /**
     * 获取圈内-所有动态
     *
     * @param circleNo 圈子编号
     * @return 结果集
     */
    @Override
    public List<MomentsDetailDto> getCircleMoments(Long circleNo) {
        List<MomentsDetailDto> result = Lists.newArrayList();
        CircleMomentsDo circleMomentsDo = new CircleMomentsDo();
        circleMomentsDo.setCircleNo(circleNo);
        List<CircleMomentsDo> circleMomentsDos = circleMomentsMapper.queryCircleMoments(circleMomentsDo);
        if (CollectionUtils.isEmpty(circleMomentsDos)) {
            return result;
        }
        for (CircleMomentsDo circleMomentsDo1 : circleMomentsDos) {
            // 动态的回复留言统计获取
            CommentDo commentDo = new CommentDo();
            commentDo.setTopicId(circleMomentsDo1.getMomentsId());
            commentDo.setTopicType(NumberDict.ONE);
            List<CommentDo> commentDos = commentDoMapper.getAllComments(commentDo);
            circleMomentsDo1.setCommentsCount(commentDos.size());
            // 动态绑定的点赞统计获取
            CirclePraiseDo circlePraiseDo = new CirclePraiseDo();
            circlePraiseDo.setMomentsId(circleMomentsDo1.getMomentsId());
            circlePraiseDo.setPraiseType(NumberDict.ONE);
            List<CirclePraiseDo> circlePraiseDos = circlePraiseMapper.getAllPraises(circlePraiseDo);
            circleMomentsDo1.setPraiseCount(circlePraiseDos.size());
            MomentsDetailDto momentsDetailDto = CircleMomentsConvert.convertToDetailDto(circleMomentsDo1);
            result.add(momentsDetailDto);
        }
        return result;
    }

    /**
     * 获取个人-所有动态
     *
     * @param userId 用户编号
     * @return 结果集
     */
    @Override
    public List<PersonalMomentsDto> getPersonalMoments(Long userId) {
        List<PersonalMomentsDto> result = Lists.newArrayList();
        PersonalMomentsDo personalMomentsDo = new PersonalMomentsDo();
        personalMomentsDo.setPublisherId(userId);
        List<PersonalMomentsDo> personalMomentsDos = personalMomentsMapper.queryPersonalMoments(personalMomentsDo);
        if (CollectionUtils.isEmpty(personalMomentsDos)) {
            return result;
        }
        for (PersonalMomentsDo personalMomentsDo1 : personalMomentsDos) {
            // 动态的回复留言统计获取
            CommentDo commentDo = new CommentDo();
            commentDo.setTopicId(personalMomentsDo1.getMomentsId());
            commentDo.setTopicType(NumberDict.ONE);
            List<CommentDo> commentDos = commentDoMapper.getAllComments(commentDo);
            personalMomentsDo1.setCommentsCount(commentDos.size());
            // 动态绑定的点赞统计获取
            CirclePraiseDo circlePraiseDo = new CirclePraiseDo();
            circlePraiseDo.setMomentsId(personalMomentsDo1.getMomentsId());
            circlePraiseDo.setPraiseType(NumberDict.ONE);
            List<CirclePraiseDo> circlePraiseDos = circlePraiseMapper.getAllPraises(circlePraiseDo);
            personalMomentsDo1.setPraiseCount(circlePraiseDos.size());
            PersonalMomentsDto personalMomentsDto = PersonalMomentsConvert.convertToDto(personalMomentsDo1);
            result.add(personalMomentsDto);
        }
        return result;
    }

    /**
     * 朋友圈动态-动态详情-查询
     *
     * @param momentsId 动态编号
     * @return 结果集
     */
    @Override
    public MomentsDetailDto getPersonalMomentDetail(Long momentsId) {
        MomentsDetailDto momentsDetailDto = new MomentsDetailDto();
        PersonalMomentsDo personalMomentsDo = new PersonalMomentsDo();
        personalMomentsDo.setMomentsId(momentsId);
        List<PersonalMomentsDo> personalMomentsDos = personalMomentsMapper.queryPersonalMoments(personalMomentsDo);
        if (CollectionUtils.isEmpty(personalMomentsDos)) {
            return momentsDetailDto;
        }
        PersonalMomentsDo personalMomentsDo1 = personalMomentsDos.get(NumberDict.ZERO);
        // 动态绑定的点赞统计获取
        CirclePraiseDo circlePraiseDo = new CirclePraiseDo();
        circlePraiseDo.setMomentsId(personalMomentsDo1.getMomentsId());
        circlePraiseDo.setPraiseType(NumberDict.ONE);
        List<CirclePraiseDo> circlePraiseDos = circlePraiseMapper.getAllPraises(circlePraiseDo);
        personalMomentsDo1.setPraiseCount(circlePraiseDos.size());
        momentsDetailDto = PersonalMomentsConvert.convertToDetailDto(personalMomentsDo1);
        // 动态的回复留言统计获取
        CommentDo commentDo = new CommentDo();
        commentDo.setTopicId(personalMomentsDo1.getMomentsId());
        commentDo.setTopicType(NumberDict.ONE);
        List<CommentDo> commentDos = commentDoMapper.getAllComments(commentDo);
        List<CommentDto> commentDtos = Lists.newArrayList();
        for (CommentDo commentDo1 : commentDos) {
            commentDtos.add(CommentConvert.convertToDto(commentDo1));
        }

        momentsDetailDto.setCommentList(commentDtos);
        return momentsDetailDto;
    }

    /**
     * 朋友圈-圈内动态-动态详情-查询
     *
     * @param momentsId 动态编号
     * @param circleNo  圈子编号
     * @return 结果集
     */
    @Override
    public MomentsDetailDto getCircleMomentDetail(Long momentsId, Long circleNo) {
        MomentsDetailDto momentsDetailDto = new MomentsDetailDto();
        CircleMomentsDo circleMomentsDo = new CircleMomentsDo();
        circleMomentsDo.setCircleNo(circleNo);
        circleMomentsDo.setMomentsId(circleNo);
        List<CircleMomentsDo> circleMomentsDos = circleMomentsMapper.queryCircleMoments(circleMomentsDo);
        if (CollectionUtils.isEmpty(circleMomentsDos)) {
            return momentsDetailDto;
        }
        CircleMomentsDo circleMomentsDo1 = circleMomentsDos.get(NumberDict.ZERO);
        // 动态绑定的点赞统计获取
        CirclePraiseDo circlePraiseDo = new CirclePraiseDo();
        circlePraiseDo.setMomentsId(circleMomentsDo1.getMomentsId());
        circlePraiseDo.setPraiseType(NumberDict.ONE);
        List<CirclePraiseDo> circlePraiseDos = circlePraiseMapper.getAllPraises(circlePraiseDo);
        circleMomentsDo1.setPraiseCount(circlePraiseDos.size());
        momentsDetailDto = CircleMomentsConvert.convertToDetailDto(circleMomentsDo1);
        // 动态的回复留言统计获取
        CommentDo commentDo = new CommentDo();
        commentDo.setTopicId(circleMomentsDo1.getMomentsId());
        commentDo.setTopicType(NumberDict.ONE);
        List<CommentDo> commentDos = commentDoMapper.getAllComments(commentDo);
        List<CommentDto> commentDtos = Lists.newArrayList();
        for (CommentDo commentDo1 : commentDos) {
            commentDtos.add(CommentConvert.convertToDto(commentDo1));
        }
        momentsDetailDto.setCommentList(commentDtos);
        return momentsDetailDto;
    }

    /**
     * 朋友圈动态-添加评论
     *
     * @param commentVo 评论内容
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addComments(CommentVo commentVo) {
        Long commentNo = orderIdManager.orderIdCreate(RedisDict.MOMENTS_COMMENT_NO_KEY);
        commentVo.setCommentId(String.valueOf(commentNo));
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setUserId(Long.valueOf(commentVo.getFromUid()));
        UserInfoDo userInfo = userInfoMapper.selectOne(userInfoDo);
        commentVo.setFromUidName(userInfo.getUserRealName());
        if(TechGoalObjects.isNotEmpty(commentVo.getToUid())){
            UserInfoDo userInfoReply = userInfoMapper.selectOne(userInfoDo);
            commentVo.setToUidName(userInfoReply.getUserRealName());
        }
        commentVo.setCreateBy(userInfo.getUserRealName());
        CommentDo commentDo = CommentConvert.convertToDo(commentVo);
        commentDo.setEnabled(NumberDict.ONE);
        commentDo.setVaildStatus(NumberDict.ONE);
        commentDoMapper.insert(commentDo);
    }

    /**
     * 朋友圈动态-点赞
     *
     * @param circlePraiseVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addThumbUp(CirclePraiseVo circlePraiseVo) {
        Long praiseNo = orderIdManager.orderIdCreate(RedisDict.MOMENTS_PRAISE_NO_KEY);
        circlePraiseVo.setPraiseId(String.valueOf(praiseNo));
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setUserId(Long.valueOf(circlePraiseVo.getUserId()));
        UserInfoDo userInfo = userInfoMapper.selectOne(userInfoDo);
        circlePraiseVo.setCreateBy(userInfo.getUserRealName());
        circlePraiseVo.setUpdateBy(userInfo.getUserRealName());
        CirclePraiseDo circlePraiseDo = MomentsConvert.convertToPraiseDo(circlePraiseVo);
        circlePraiseDo.setStatus(NumberDict.ONE);
        circlePraiseMapper.insert(circlePraiseDo);
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
        /