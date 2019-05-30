package com.techGoal.convert;

import com.techGoal.pojo.dao.CircleMomentsDo;
import com.techGoal.pojo.dao.PersonalMomentsDo;
import com.techGoal.pojo.vo.MomentsVo;
import com.techGoal.utils.TechGoalObjects;

import java.util.Date;

/**
 * description : Moments 转换类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/30 11:07
 */
public class MomentsConvert {

    public static PersonalMomentsDo convertToPerMomentsDo(MomentsVo momentsVo) {
        if (momentsVo == null) {
            return null;
        }
        PersonalMomentsDo personalMomentsDo = new PersonalMomentsDo();
        if(TechGoalObjects.isNotEmpty(momentsVo.getMomentsId())){
            personalMomentsDo.setMomentsId(Long.valueOf(momentsVo.getMomentsId()));
        }
        personalMomentsDo.setTitle(momentsVo.getTitle());
        personalMomentsDo.setContent(momentsVo.getContent());
        personalMomentsDo.setImages(momentsVo.getImages());
        if(TechGoalObjects.isNotEmpty(momentsVo.getStatus())){
            personalMomentsDo.setStatus(Integer.valueOf(momentsVo.getStatus()));
        }
        if(TechGoalObjects.isNotEmpty(momentsVo.getCommentFlag())){
            personalMomentsDo.setCommentFlag(Integer.valueOf(momentsVo.getCommentFlag()));
        }
        personalMomentsDo.setLocation(momentsVo.getLocation());
        if(TechGoalObjects.isNotEmpty(momentsVo.getPublisherId())){
            personalMomentsDo.setPublisherId(Long.valueOf(momentsVo.getPublisherId()));
        }
        personalMomentsDo.setUpdateBy(momentsVo.getUpdateBy());
        if(TechGoalObjects.isNotEmpty(momentsVo.getPublisherTime())){
            personalMomentsDo.setPublisherTime(new Date(momentsVo.getPublisherTime()));
        }
        if(TechGoalObjects.isNotEmpty(momentsVo.getUpdateAt())){
            personalMomentsDo.setUpdateAt(new Date(momentsVo.getUpdateAt()));
        }
        return personalMomentsDo;
    }

    public static MomentsVo convertToPerMomentsVo(PersonalMomentsDo momentsDo) {
        if (momentsDo == null) {
            return null;
        }
        MomentsVo momentsVo = new MomentsVo();
        if(TechGoalObjects.isNotEmpty(momentsDo.getMomentsId())){
            momentsVo.setMomentsId(String.valueOf(momentsDo.getMomentsId()));
        }
        momentsVo.setTitle(momentsDo.getTitle());
        momentsVo.setContent(momentsDo.getContent());
        momentsVo.setImages(momentsDo.getImages());
        if(TechGoalObjects.isNotEmpty(momentsDo.getStatus())){
            momentsVo.setStatus(String.valueOf(momentsDo.getStatus()));
        }
        if(TechGoalObjects.isNotEmpty(momentsDo.getCommentFlag())){
            momentsVo.setCommentFlag(String.valueOf(momentsDo.getCommentFlag()));
        }
        momentsVo.setLocation(momentsDo.getLocation());
        if(TechGoalObjects.isNotEmpty(momentsDo.getPublisherId())){
            momentsVo.setPublisherId(String.valueOf(momentsDo.getPublisherId()));
        }
        momentsVo.setUpdateBy(momentsDo.getUpdateBy());
        if(TechGoalObjects.isNotEmpty(momentsDo.getPublisherTime())){
            momentsVo.setPublisherTime(String.valueOf(momentsDo.getPublisherTime()));
        }
        if(TechGoalObjects.isNotEmpty(momentsDo.getUpdateAt())){
            momentsVo.setUpdateAt(String.valueOf(momentsDo.getUpdateAt()));
        }
        return momentsVo;
    }

    public static CircleMomentsDo convertToCirMomentsDo(MomentsVo momentsVo) {
        if (momentsVo == null) {
            return null;
        }
        CircleMomentsDo circleMomentsDo = new CircleMomentsDo();
        if(TechGoalObjects.isNotEmpty(momentsVo.getMomentsId())){
            circleMomentsDo.setMomentsId(Long.valueOf(momentsVo.getMomentsId()));
        }
        if(TechGoalObjects.isNotEmpty(momentsVo.getCircleNo())){
            circleMomentsDo.setCircleNo(Long.valueOf(momentsVo.getCircleNo()));
        }
        circleMomentsDo.setTitle(momentsVo.getTitle());
        circleMomentsDo.setContent(momentsVo.getContent());
        circleMomentsDo.setImages(momentsVo.getImages());
        if(TechGoalObjects.isNotEmpty(momentsVo.getStatus())){
            circleMomentsDo.setStatus(Integer.valueOf(momentsVo.getStatus()));
        }
        if(TechGoalObjects.isNotEmpty(momentsVo.getCommentFlag())){
            circleMomentsDo.setCommentFlag(Integer.valueOf(momentsVo.getCommentFlag()));
        }
        circleMomentsDo.setLocation(momentsVo.getLocation());
        if(TechGoalObjects.isNotEmpty(momentsVo.getPublisherId())){
            circleMomentsDo.setPublisherId(Long.valueOf(momentsVo.getPublisherId()));
        }
        circleMomentsDo.setUpdateBy(momentsVo.getUpdateBy());
        if(TechGoalObjects.isNotEmpty(momentsVo.getPublisherTime())){
            circleMomentsDo.setPublisherTime(new Date(momentsVo.getPublisherTime()));
        }
        if(TechGoalObjects.isNotEmpty(momentsVo.getUpdateAt())){
            circleMomentsDo.setUpdateAt(new Date(momentsVo.getUpdateAt()));
        }
        return circleMomentsDo;
    }
}
