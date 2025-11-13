package com.wisteria.service.impl;

import com.wisteria.domain.Comment;
import com.wisteria.mapper.CommentMapper;
import com.wisteria.service.ICommentService;

import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements ICommentService {

    private static final CommentMapper commentMapper = new CommentMapper();

    /**
     * 获取数据
     */
    @Override
    public List<Comment> fetchCommentList(String userName) {
//        return commentMapper.fetchCommentList(userName);
        return commentMapper.fetchAllComment(userName);
    }
}
