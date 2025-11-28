package com.wisteria.service.impl;

import com.wisteria.domain.Comment;
import com.wisteria.domain.User;
import com.wisteria.domain.vo.CommentVO;
import com.wisteria.mapper.CommentMapper;
import com.wisteria.mapper.UserMapper;
import com.wisteria.service.ICommentService;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements ICommentService {

    private static final CommentMapper commentMapper = new CommentMapper();
    private static final UserMapper userMapper = new UserMapper();

    /**
     * 根据用户名获取用户评论
     */
    @Override
    public List<Comment> fetchCommentList(String userName) {
        return commentMapper.fetchAllComment(userName);
    }

    /**
     * 根据文章ID获取文章评论
     */
    @Override
    public List<CommentVO> fetchCommentListByNoteID(String noteID) {
        List<CommentVO> list = new ArrayList<>();
        List<Comment> comments = commentMapper.fetchCommentListByNoteID(noteID);
        for (Comment comment : comments) {
            String userName = comment.getUserName();
            System.out.println("userName:" + userName);
            User user = userMapper.findByUserName(userName);
            CommentVO commentVO = CommentVO.builder()
                    .commentId(comment.getCommentId())
                    .noteId(comment.getNoteId())
                    .userName(comment.getUserName())
                    .commentTitle(comment.getCommentTitle())
                    .commentContent(comment.getCommentContent())
                    .remoteIP(comment.getRemoteIP())
                    .createTime(comment.getCreateTime())
                    .user(user)
                    .build();
            list.add(commentVO);
        }
        return list;
    }

    /**
     * 新增评论
     */
    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    /**
     * 根据NoteID删除评论
     */
    @Override
    public void deleteCommentByNoteID(String noteID) {
        commentMapper.deleteCommentByNoteID(noteID);
    }
}
