package com.techGoal.convert;

import com.techGoal.pojo.dao.CircleMomentsDo;
import com.techGoal.pojo.dto.MomentsDetailDto;
import com.techGoal.utils.DateUtil;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/9 10:55
 */
public class CircleMomentsConvert {

    public static MomentsDetailDto convertToDetailDto(CircleMomentsDo circleMomentsDo) {
        if (circleMomentsDo == null) {
            return null;
        }
        MomentsDetailDto momentsDetailDto = new MomentsDetailDto();
        momentsDetailDto.setMomentsId(String.valueOf(circleMomentsDo.getMomentsId()));
        momentsDetailDto.setCircleNo(String.valueOf(circleMomentsDo.getCircleNo()));
        momentsDetailDto.setTitle(circleMomentsDo.getTitle());
        momentsDetailDto.setContent(circleMomentsDo.getContent());
        momentsDetailDto.setImages(circleMomentsDo.getImages());
        momentsDetailDto.setStatus(String.valueOf(circleMomentsDo.getStatus()));
        momentsDetailDto.setCommentFlag(String.valueOf(circleMomentsDo.getCommentFlag()));
        momentsDetailDto.setLocation(circleMomentsDo.getLocation());
        momentsDetailDto.setPublisherId(String.valueOf(circleMomentsDo.getPublisherId()));
        momentsDetailDto.setUpdateBy(circleMomentsDo.getUpdateBy());
        momentsDetailDto.setPublisherTime(DateUtil.formatFull(circleMomentsDo.getPublisherTime()));
        momentsDetailDto.setUpdateAt(DateUtil.formatFull(circleMomentsDo.getUpdateAt()));
        momentsDetailDto.setCommentsCount(String.valueOf(circleMomentsDo.getCommentsCount()));
        momentsDetailDto.setPraiseCount(String.valueOf(circleMomentsDo.getPraiseCount()));
        return momentsDetailDto;
    }
}
