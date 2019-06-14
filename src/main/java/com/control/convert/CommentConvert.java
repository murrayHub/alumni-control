package com.techGoal.convert;

import com.techGoal.pojo.dao.CommentDo;
import com.techGoal.pojo.dto.CommentDto;
import com.techGoal.pojo.vo.CommentVo;
import com.techGoal.utils.DateUtil;
import com.techGoal.utils.TechGoalObjects;

import java.util.Date;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/7 16:48
 */
public class CommentConvert {
    public static CommentDto convertToDto(CommentDo commentDo) {
        if (commentDo == null) {
            return null;
        }
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(String.valueOf(commentDo.getCommentId()));
        commentDto.setTopicId(String.valueOf(commentDo.getTopicId()));
        commentDto.setFromUid(String.valueOf(commentDo.getFromUid()));
        commentDto.setContent(commentDo.getContent());
        commentDto.setVaildStatus(String.valueOf(commentDo.getVaildStatus()));
        commentDto.setEnabled(String.valueOf(commentDo.getEnabled()));
        commentDto.setCreateBy(commentDo.getCreateBy());
        commentDto.setUpdateBy(commentDo.getUpdateBy());
        commentDto.setCreateAt(DateUtil.formatFull(commentDo.getCreateAt()));
        commentDto.setUpdateAt(DateUtil.formatFull(commentDo.getUpdateAt()));
        commentDto.setTopicType(String.valueOf(commentDo.getTopicType()));
        commentDto.setToUid(String.valueOf(commentDo.getToUid()));
        commentDto.setFromUidName(commentDo.getFromUidName());
        commentDto.setToUidName(commentDo.getToUidName());
        return commentDto;
    }

    public static CommentDo convertToDo(CommentVo commentVo) {
        if (commentVo == null) {
            return null;
        }
        CommentDo commentDo = new CommentDo();
        commentDo.setCommentId(Long.valueOf(commentVo.getCommentId()));
        commentDo.setTopicId(Long.valueOf(commentVo.getTopicId()));
        commentDo.setFromUid(Long.valueOf(commentVo.getFromUid()));
        commentDo.setContent(commentVo.getContent());
        if(TechGoalObjects.isNotEmpty(commentVo.getVaildStatus())){
            commentDo.setVaildStatus(Integer.valueOf(commentVo.getVaildStatus()));
        }
        if(TechGoalObjects.isNotEmpty(commentVo.getEnabled())){
            commentDo.setEnabled(Integer.valueOf(commentVo.getEnabled()));
        }
        commentDo.setCreateBy(commentVo.getCreateBy());
        commentDo.setUpdateBy(commentVo.getUpdateBy());
        commentDo.setCreateAt(new Date());
        commentDo.setUpdateAt(new Date());
        commentDo.setTopicType(Integer.valueOf(commentVo.getTopicType()));
        if(TechGoalObjects.isNotEmpty(commentVo.getToUid())){
            commentDo.setToUid(Long.valueOf(commentVo.getToUid()));
        }
        commentDo.setFromUidName(commentVo.getFromUidName());
        commentDo.setToUidName(commentVo.getToUidName());
        return commentDo;
    }

}
