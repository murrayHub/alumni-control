package com.techGoal.convert;

import com.techGoal.pojo.dao.CircleApplyDo;
import com.techGoal.pojo.vo.CircleApplyVo;
import com.techGoal.utils.TechGoalObjects;

/**
 * description : 入圈申请
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 10:17
 */
public class CircleApplyConvert {

    public static CircleApplyVo circleApplyDoToConvertVo(CircleApplyDo circleApplyDo) {
        if (circleApplyDo == null) {
            return null;
        }
        CircleApplyVo circleApplyVo = new CircleApplyVo();

        if(!TechGoalObjects.isEmpty(circleApplyDo.getApplyCircleId())){
            circleApplyVo.setApplyCircleId(String.valueOf(circleApplyDo.getApplyCircleId()));
        }
        if(!TechGoalObjects.isEmpty(circleApplyDo.getCircleNo())){
            circleApplyVo.setCircleNo(String.valueOf(circleApplyDo.getCircleNo()));
        }
        circleApplyVo.setCircleName(circleApplyDo.getCircleName());
        if(!TechGoalObjects.isEmpty(circleApplyDo.getUserId())){
            circleApplyVo.setApplyUserId(String.valueOf(circleApplyDo.getUserId()));
        }
        circleApplyVo.setApplyContent(circleApplyDo.getApplyContent());
        if(!TechGoalObjects.isEmpty(circleApplyDo.getState())){
            circleApplyVo.setState(String.valueOf(circleApplyDo.getState()));
        }
        circleApplyVo.setCreateAt(circleApplyDo.getCreateAt());
        return circleApplyVo;
    }


}
