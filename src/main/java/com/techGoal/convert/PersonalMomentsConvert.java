package com.techGoal.convert;

import com.techGoal.pojo.dao.PersonalMomentsDo;
import com.techGoal.pojo.dto.PersonalMomentsDetailDto;
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

    public static PersonalMomentsDetailDto convertToDetailDto(PersonalMomentsDo personalMomentsDo) {
        if (personalMomentsDo == null) {
            return null;
        }
        PersonalMomentsDetailDto personalMomentsDetailDto = new PersonalMomentsDetailDto();
        personalMomentsDetailDto.setMomentsId(String.valueOf(personalMomentsDo.getMomentsId()));
        personalMomentsDetailDto.setTitle(personalMomentsDo.getTitle());
        personalMomentsDetailDto.setContent(personalMomentsDo.getContent());
        personalMomentsDetailDto.setImages(personalMomentsDo.getImages());
        personalMomentsDetailDto.setStatus(String.valueOf(personalMomentsDo.getStatus()));
        personalMomentsDetailDto.setCommentFlag(String.valueOf(personalMomentsDo.getCommentFlag()));
        personalMomentsDetailDto.setLocation(personalMomentsDo.getLocation());
        personalMomentsDetailDto.setPublisherId(String.valueOf(personalMomentsDo.getPublisherId()));
        personalMomentsDetailDto.setUpdateBy(personalMomentsDo.getUpdateBy());
        personalMomentsDetailDto.setPublisherTime(DateUtil.formatFull(personalMomentsDo.getPublisherTime()));
        personalMomentsDetailDto.setUpdateAt(DateUtil.formatFull(personalMomentsDo.getUpdateAt()));
        personalMomentsDetailDto.setCommentsCount(String.valueOf(personalMomentsDo.getCommentsCount()));
        personalMomentsDetailDto.setPraiseCount(String.valueOf(personalMomentsDo.getPraiseCount()));
        return personalMomentsDetailDto;
    }
}
