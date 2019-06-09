package com.techGoal.convert;

import com.techGoal.pojo.dao.PersonalMomentsDo;
import com.techGoal.pojo.dto.MomentsDetailDto;
import com.techGoal.pojo.dto.PersonalMomentsDto;
import com.techGoal.utils.DateUtil;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/7 15:16
 */
public class PersonalMomentsConvert {
    public static PersonalMomentsDto convertToDto(PersonalMomentsDo personalMomentsDo) {
        if (personalMomentsDo == null) {
            return null;
        }
        PersonalMomentsDto personalMomentsDto = new PersonalMomentsDto();
        personalMomentsDto.setMomentsId(String.valueOf(personalMomentsDo.getMomentsId()));
        personalMomentsDto.setTitle(personalMomentsDo.getTitle());
        personalMomentsDto.setContent(personalMomentsDo.getContent());
        personalMomentsDto.setImages(personalMomentsDo.getImages());
        personalMomentsDto.setStatus(String.valueOf(personalMomentsDo.getStatus()));
        personalMomentsDto.setCommentFlag(String.valueOf(personalMomentsDo.getCommentFlag()));
        personalMomentsDto.setLocation(personalMomentsDo.getLocation());
        personalMomentsDto.setPublisherId(String.valueOf(personalMomentsDo.getPublisherId()));
        personalMomentsDto.setUpdateBy(personalMomentsDo.getUpdateBy());
        personalMomentsDto.setPublisherTime(DateUtil.formatFull(personalMomentsDo.getPublisherTime()));
        personalMomentsDto.setUpdateAt(DateUtil.formatFull(personalMomentsDo.getUpdateAt()));
        personalMomentsDto.setCommentsCount(String.valueOf(personalMomentsDo.getCommentsCount()));
        personalMomentsDto.setPraiseCount(String.valueOf(personalMomentsDo.getPraiseCount()));
        return personalMomentsDto;
    }

    public static MomentsDetailDto convertToDetailDto(PersonalMomentsDo personalMomentsDo) {
        if (personalMomentsDo == null) {
            return null;
        }
        MomentsDetailDto momentsDetailDto = new MomentsDetailDto();
        momentsDetailDto.setMomentsId(String.valueOf(personalMomentsDo.getMomentsId()));
        momentsDetailDto.setTitle(personalMomentsDo.getTitle());
        momentsDetailDto.setContent(personalMomentsDo.getContent());
        momentsDetailDto.setImages(personalMomentsDo.getImages());
        momentsDetailDto.setStatus(String.valueOf(personalMomentsDo.getStatus()));
        momentsDetailDto.setCommentFlag(String.valueOf(personalMomentsDo.getCommentFlag()));
        momentsDetailDto.setLocation(personalMomentsDo.getLocation());
        momentsDetailDto.setPublisherId(String.valueOf(personalMomentsDo.getPublisherId()));
        momentsDetailDto.setUpdateBy(personalMomentsDo.getUpdateBy());
        momentsDetailDto.setPublisherTime(DateUtil.formatFull(personalMomentsDo.getPublisherTime()));
        momentsDetailDto.setUpdateAt(DateUtil.formatFull(personalMomentsDo.getUpdateAt()));
        momentsDetailDto.setCommentsCount(String.valueOf(personalMomentsDo.getCommentsCount()));
        momentsDetailDto.setPraiseCount(String.valueOf(personalMomentsDo.getPraiseCount()));
        return momentsDetailDto;
    }
}
