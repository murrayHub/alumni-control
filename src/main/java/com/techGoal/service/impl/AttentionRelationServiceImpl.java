package com.techGoal.service.impl;

import com.techGoal.convert.AttentionRelationConvert;
import com.techGoal.dict.NumberDict;
import com.techGoal.enums.ErrorCodeEnum;
import com.techGoal.exception.BizServiceException;
import com.techGoal.mapper.AttentionRelationMapper;
import com.techGoal.pojo.dao.AttentionRelationDo;
import com.techGoal.pojo.vo.AttentionRelationVo;
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
                throw new BizServiceException(ErrorCodeEnum.ERROR_CODE_000012);
            }
        }
    }
}
