package com.wisteria.service;

import com.wisteria.domain.Comment;
import com.wisteria.domain.vo.CommentVO;

import java.util.List;

public interface ICommentService {
    /**
     * 根据用户名获取用户评论
     */
    List<Comment> fetchCommentList(String userName);

    /**
     * 根据文章ID获取文章评论
     */
    List<CommentVO> fetchCommentListByNoteID(String noteID);

    /**
     * 新增评论
     */
    void insertComment(Comment comment);

    /**
     * 根据NoteID删除评论
     */
    void deleteCommentByNoteID(String noteID);
}
