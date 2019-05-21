package com.techGoal.service.impl;

import com.google.common.collect.Lists;
import com.techGoal.convert.AttentionRelationConvert;
import com.techGoal.convert.UserInfoConvert;
import com.techGoal.dict.NumberDict;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.mapper.AttentionRelationMapper;
import com.techGoal.pojo.dao.AttentionRelationDo;
import com.techGoal.pojo.dao.UserInfoDo;
import com.techGoal.pojo.vo.AttentionRelationVo;
import com.techGoal.pojo.vo.UserInfoVo;
import com.techGoal.service.AttentionRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * description : 关注和被关注者关系管理-服务层-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 22:24
 */
@Slf4j
@Service
public class AttentionRelationServiceImpl implements AttentionRelationService {

    /**
     * 关注和被关注关系管理 Mapper
     */
    @Autowired
    private AttentionRelationMapper attentionRelationMapper;
    /**
     * 加关注
     *
     * @param attentionRelationVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payAttention(AttentionRelationVo attentionRelationVo) {
        // 查询两者是否已存在关注关系
        List<AttentionRelationDo> attentionRelationDos =  attentionRelationMapper.selectRelation(AttentionRelationConvert.AttentionRelationVoToDo(attentionRelationVo));
        if(CollectionUtils.isEmpty(attentionRelationDos)){
            // 创建两者的关系
            AttentionRelationDo attentionRelationDoCreate = AttentionRelationConvert.AttentionRelationVoToDo(attentionRelationVo);
            attentionRelationDoCreate.setRelationType(NumberDict.TWO);
            attentionRelationDoCreate.setCreateAt(new Date());
            attentionRelationDoCreate.setCreateBy(attentionRelationVo.getUserId());
            attentionRelationMapper.insert(attentionRelationDoCreate);
        } else {
            // 更新两者的关系
            int relationType =attentionRelationDos.get(NumberDict.ZERO).getRelationType();
            if(relationType != NumberDict.THREE){
                AttentionRelationDo attentionRelationDoUpd = new AttentionRelationDo();
                attentionRelationDoUpd.setRelationType(NumberDict.THREE);
                attentionRelationDoUpd.setUserId(Long.valueOf(attentionRelationVo.getUserId()));
                attentionRelationDoUpd.setTargetObject(Long.valueOf(attentionRelationVo.getTargetObject()));
                attentionRelationDoUpd.setUpdateBy(attentionRelationVo.getUserId());
                attentionRelationMapper.updateRelation(attentionRelationDoUpd);
            } else {
                log.error("加关注，异常:{}", ErrorCodeEnum.ERROR_CODE_000012.getErrorDesc());
                throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000012);
            }
        }
    }

    /**
     * 取消关注
     *
     * @param attentionRelationVo 请求参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPayAttention(AttentionRelationVo attentionRelationVo) {
        // 查询两者是否已存在关注关系
        List<AttentionRelationDo> attentionRelationDos =  attentionRelationMapper.selectRelation(AttentionRelationConvert.AttentionRelationVoToDo(attentionRelationVo));
        if(!CollectionUtils.isEmpty(attentionRelationDos)){
            Long userIdCurrent = attentionRelationDos.get(NumberDict.ZERO).getUserId();
            int relateTypeCurrent = attentionRelationDos.get(NumberDict.ZERO).getRelationType();
            if(attentionRelationVo.getUserId().equals(String.valueOf(userIdCurrent))){
                if(relateTypeCurrent == NumberDict.ONE){
                    //单向关系,userId为被关注者,userIdCurrent为关注者
                    log.error("取消关注，异常:{}", ErrorCodeEnum.ERROR_CODE_000013.getErrorDesc());
                    throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000013);
                }else if(relateTypeCurrent == NumberDict.TWO){
                    //单向关系,userId为关注者,userIdCurrent为被关注者（删除关系记录）
                    AttentionRelationDo attentionRelationDoDte = new AttentionRelationDo();
                    attentionRelationDoDte.setUserId(Long.valueOf(attentionRelationVo.getUserId()));
                    attentionRelationDoDte.setTargetObject(Long.valueOf(attentionRelationVo.getTargetObject()));
                    attentionRelationMapper.delete(attentionRelationDoDte);
                }else {
                    //双向关系,userId,userIdCurrent互粉（更新关系记录）
                    AttentionRelationDo attentionRelationDoUpd = new AttentionRelationDo();
                    attentionRelationDoUpd.setUserId(Long.valueOf(attentionRelationVo.getUserId()));
                    attentionRelationDoUpd.setTargetObject(Long.valueOf(attentionRelationVo.getTargetObject()));
                    attentionRelationDoUpd.setRelationType(NumberDict.ONE);
                    attentionRelationMapper.updateRelationDirect(attentionRelationDoUpd);
                }
            }else {
                if(relateTypeCurrent == NumberDict.ONE){
                    //单向关系,userId为关注者,userIdCurrent为被关注者（删除关系记录）
                    AttentionRelationDo attentionRelationDoDte = new AttentionRelationDo();
                    attentionRelationDoDte.setUserId(Long.valueOf(attentionRelationVo.getUserId()));
                    attentionRelationDoDte.setTargetObject(Long.valueOf(attentionRelationVo.getTargetObject()));
                    attentionRelationMapper.delete(attentionRelationDoDte);
                }else if(relateTypeCurrent == NumberDict.TWO){
                    //单向关系,userId为被关注者,userIdCurrent为关注者
                    log.error("取消关注，异常:{}", ErrorCodeEnum.ERROR_CODE_000013.getErrorDesc());
                    throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000013);
                }else {
                    //双向关系,userId,userIdCurrent互粉（更新关系记录）
                    AttentionRelationDo attentionRelationDoUpd = new AttentionRelationDo();
                    attentionRelationDoUpd.setUserId(Long.valueOf(attentionRelationVo.getTargetObject()));
                    attentionRelationDoUpd.setTargetObject(Long.valueOf(attentionRelationVo.getUserId()));
                    attentionRelationDoUpd.setRelationType(NumberDict.TWO);
                    attentionRelationMapper.updateRelationDirect(attentionRelationDoUpd);
                }
            }
        }else {
            log.error("取消关注，异常:{}", ErrorCodeEnum.ERROR_CODE_000013.getErrorDesc());
            throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000013);
        }
    }

    /**
     * 获取所有我关注的好友
     *
     * @param attentionRelationVo 请求参数
     * @return 结果集
     */
    @Override
    public List<UserInfoVo> getPayAttentionUsers(AttentionRelationVo attentionRelationVo) {
        List<UserInfoVo> userInfoVos = Lists.newArrayList();
        AttentionRelationDo attentionRelationDo = new AttentionRelationDo();
        attentionRelationDo.setUserId(Long.valueOf(attentionRelationVo.getUserId()));
        attentionRelationDo.setUserRealname(attentionRelationVo.getUserRealName());
        List<UserInfoDo> userInfoVoList = attentionRelationMapper.getPayAttentionUsers(attentionRelationDo);
        for(UserInfoDo userInfoDo : userInfoVoList){
            UserInfoVo userInfoVo = UserInfoConvert.UserInfoDoToVo(userInfoDo);
            userInfoVos.add(userInfoVo);
        }
        return userInfoVos;
    }

    /**
     * 获取所有关注我的好友
     *
     * @param attentionRelationVo 请求参数
     * @return 结果集
     */
    @Override
    public List<UserInfoVo> getBePayedAttentionUsers(AttentionRelationVo attentionRelationVo) {
        List<UserInfoVo> userInfoVos = Lists.newArrayList();
        AttentionRelationDo attentionRelationDo = new AttentionRelationDo();
        attentionRelationDo.setUserId(Long.valueOf(attentionRelationVo.getUserId()));
        attentionRelationDo.setUserRealname(attentionRelationVo.getUserRealName());
        List<UserInfoDo> userInfoVoList = attentionRelationMapper.getBePayedAttentionUsers(attentionRelationDo);
        for(UserInfoDo userInfoDo : userInfoVoList){
            UserInfoVo userInfoVo = UserInfoConvert.UserInfoDoToVo(userInfoDo);
            userInfoVos.add(userInfoVo);
        }
        return userInfoVos;
    }

    /**
     * 获取所有尚未关注的用户
     *
     * @param attentionRelationVo 请求参数
     * @return 结果集
     */
    @Override
    public List<UserInfoVo> getNoPayAttentionUsers(AttentionRelationVo attentionRelationVo) {
        List<UserInfoVo> userInfoVos = Lists.newArrayList();
        AttentionRelationDo attentionRelationDo = new AttentionRelationDo();
        attentionRelationDo.setUserId(Long.valueOf(attentionRelationVo.getUserId()));
        List<UserInfoDo> userInfoVoList = attentionRelationMapper.getNoPayAttentionUsers(attentionRelationDo);
        for(UserInfoDo userInfoDo : userInfoVoList){
            UserInfoVo userInfoVo = UserInfoConvert.UserInfoDoToVo(userInfoDo);
            userInfoVos.add(userInfoVo);
        }
        return userInfoVos;
    }
}
