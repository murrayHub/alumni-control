package com.control.convert;

import com.control.pojo.dao.AttentionRelationDo;
import com.control.pojo.vo.AttentionRelationVo;
import com.control.utils.TechGoalObjects;
import com.techGoal.pojo.dao.AttentionRelationDo;
import com.techGoal.pojo.vo.AttentionRelationVo;
import com.techGoal.utils.TechGoalObjects;

/**
 * description : 关注和被关注关系管理
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 22:36
 */
public class AttentionRelationConvert {

    public static AttentionRelationVo AttentionRelationDoToVo(AttentionRelationDo attentionRelationDo) {
        if (attentionRelationDo == null) {
            return null;
        }
        AttentionRelationVo attentionRelationVo = new AttentionRelationVo();
        if(!TechGoalObjects.isEmpty(attentionRelationVo.getUserId())){
            attentionRelationVo.setUserId(String.valueOf(attentionRelationDo.getUserId()));
        }
        if(!TechGoalObjects.isEmpty(attentionRelationVo.getTargetObject())){
            attentionRelationVo.setTargetObject(String.valueOf(attentionRelationDo.getTargetObject()));
        }
        if(!TechGoalObjects.isEmpty(attentionRelationVo.getRelationType())){
            attentionRelationVo.setRelationType(String.valueOf(attentionRelationDo.getRelationType()));
        }
        attentionRelationVo.setCreateBy(attentionRelationDo.getCreateBy());
        attentionRelationVo.setUpdateBy(attentionRelationDo.getUpdateBy());
        attentionRelationVo.setCreateAt(attentionRelationDo.getCreateAt());
        attentionRelationVo.setUpdateAt(attentionRelationDo.getUpdateAt());
        return attentionRelationVo;
    }

    public static AttentionRelationDo AttentionRelationVoToDo(AttentionRelationVo attentionRelationVo) {
        if (attentionRelationVo == null) {
            return null;
        }
        AttentionRelationDo attentionRelationDo = new AttentionRelationDo();
        if(!TechGoalObjects.isEmpty(attentionRelationVo.getUserId())){
            attentionRelationDo.setUserId(Long.valueOf(attentionRelationVo.getUserId()));
        }
        if(!TechGoalObjects.isEmpty(attentionRelationVo.getTargetObject())){
            attentionRelationDo.setTargetObject(Long.valueOf(attentionRelationVo.getTargetObject()));
        }
        if(!TechGoalObjects.isEmpty(attentionRelationVo.getRelationType())){
            attentionRelationDo.setRelationType(Integer.valueOf(attentionRelationVo.getRelationType()));
        }
        attentionRelationDo.setCreateBy(attentionRelationVo.getCreateBy());
        attentionRelationDo.setUpdateBy(attentionRelationVo.getUpdateBy());
        attentionRelationDo.setCreateAt(attentionRelationVo.getCreateAt());
        attentionRelationDo.setUpdateAt(attentionRelationVo.getUpdateAt());
        return attentionRelationDo;
    }
}
