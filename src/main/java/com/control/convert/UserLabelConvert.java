package com.techGoal.convert;

import com.techGoal.pojo.dao.UserLabelDo;
import com.techGoal.pojo.vo.UserLabelVo;
import com.techGoal.utils.DateUtil;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 16:33
 */
public class UserLabelConvert {
    public static UserLabelVo convertToVo(UserLabelDo userLabelDo) {
        if (userLabelDo == null) {
            return null;
        }
        UserLabelVo userLabelVo = new UserLabelVo();
        userLabelVo.setLabelId(String.valueOf(userLabelDo.getLabelId()));
        userLabelVo.setLabelName(userLabelDo.getLabelName());
        userLabelVo.setStatus(String.valueOf(userLabelDo.getStatus()));
        userLabelVo.setCreateBy(userLabelDo.getCreateBy());
        userLabelVo.setUpdateBy(userLabelDo.getUpdateBy());
        userLabelVo.setCreateAt(DateUtil.formatFull(userLabelDo.getCreateAt()));
        userLabelVo.setUpdateAt(DateUtil.formatFull(userLabelDo.getUpdateAt()));
        return userLabelVo;
    }
}
