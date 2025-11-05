package com.wisteria.service.impl;

import com.wisteria.mapper.CommentMapper;
import com.wisteria.service.ICommentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements ICommentService {

    private static final CommentMapper commentMapper = new CommentMapper();

    /**
     * 获取数据
     */
    @Override
    public List<Map<String, Object>> fetchCommentList(String userName) throws SQLException {
        return commentMapper.fetchCommentList(userName);
    }
}
