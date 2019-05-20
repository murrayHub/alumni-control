package com.techGoal.convert;

import com.techGoal.pojo.bo.CircleBo;
import com.techGoal.pojo.dao.CircleDo;
import com.techGoal.pojo.vo.CircleVo;

/**
 * description : 圈子信息
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/20 10:17
 */
public class CircleConvert {

    public static CircleBo circleVoToConvertBo(CircleVo circleVo) {
        if (circleVo == null) {
            return null;
        }
        CircleBo circleBo = new CircleBo();
        circleBo.setCircleNo(circleVo.getCircleNo());
        circleBo.setCircleHostNo(circleVo.getCircleHostNo());
        circleBo.setCircleName(circleVo.getCircleName());
        circleBo.setCircleTheme(circleVo.getCircleTheme());
        circleBo.setCircleLabel(circleVo.getCircleLabel());
        circleBo.setImage(circleVo.getImage());
        circleBo.setCreateBy(circleVo.getCreateBy());
        circleBo.setUpdateBy(circleVo.getUpdateBy());
        circleBo.setCreateAt(circleVo.getCreateAt());
        circleBo.setUpdateAt(circleVo.getUpdateAt());
        circleBo.setMemberCounts(circleVo.getMemberCounts());
        return circleBo;
    }

    public static CircleVo circleBoToConvertVo(CircleBo circleBo) {
        if (circleBo == null) {
            return null;
        }
        CircleVo circleVo = new CircleVo();
        circleVo.setCircleNo(circleBo.getCircleNo());
        circleVo.setCircleHostNo(circleBo.getCircleHostNo());
        circleVo.setCircleName(circleBo.getCircleName());
        circleVo.setCircleTheme(circleBo.getCircleTheme());
        circleVo.setCircleLabel(circleBo.getCircleLabel());
        circleVo.setImage(circleBo.getImage());
        circleVo.setCreateBy(circleBo.getCreateBy());
        circleVo.setUpdateBy(circleBo.getUpdateBy());
        circleVo.setCreateAt(circleBo.getCreateAt());
        circleVo.setUpdateAt(circleBo.getUpdateAt());
        circleVo.setMemberCounts(circleBo.getMemberCounts());
        return circleVo;
    }

    public static CircleBo circleDoToConvertBo(CircleDo circleDo) {
        if (circleDo == null) {
            return null;
        }
        CircleBo circleBo = new CircleBo();
        circleBo.setCircleNo(String.valueOf(circleDo.getCircleNo()));
        circleBo.setCircleHostNo(String.valueOf(circleDo.getCircleHostNo()));
        circleBo.setCircleName(circleDo.getCircleName());
        circleBo.setCircleTheme(circleDo.getCircleTheme());
        circleBo.setCircleLabel(circleDo.getCircleLabel());
        circleBo.setImage(circleDo.getImage());
        circleBo.setCreateBy(circleDo.getCreateBy());
        circleBo.setUpdateBy(circleDo.getUpdateBy());
        circleBo.setCreateAt(circleDo.getCreateAt());
        circleBo.setUpdateAt(circleDo.getUpdateAt());
        return circleBo;
    }

    public static CircleDo circleBoToConvertDo(CircleBo circleBo) {
        if (circleBo == null) {
            return null;
        }
        CircleDo circleDo = new CircleDo();
        circleDo.setCircleNo(Long.valueOf(circleBo.getCircleNo()));
        circleDo.setCircleHostNo(Long.valueOf(circleBo.getCircleHostNo()));
        circleDo.setCircleName(circleBo.getCircleName());
        circleDo.setCircleTheme(circleBo.getCircleTheme());
        circleDo.setCircleLabel(circleBo.getCircleLabel());
        circleDo.setImage(circleBo.getImage());
        circleDo.setCreateBy(circleBo.getCreateBy());
        circleDo.setUpdateBy(circleBo.getUpdateBy());
        circleDo.setCreateAt(circleBo.getCreateAt());
        circleDo.setUpdateAt(circleBo.getUpdateAt());
        return circleDo;
    }
}
