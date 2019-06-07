package com.techGoal.convert;

import com.techGoal.pojo.dao.CommentDo;
import com.techGoal.pojo.dto.CommentDto;
import com.techGoal.utils.DateUtil;

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
}
