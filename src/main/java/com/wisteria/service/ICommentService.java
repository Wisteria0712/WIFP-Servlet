package com.wisteria.service;

import com.wisteria.domain.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ICommentService {
    /**
     * 获取数据
     */
    List<Comment> fetchCommentList(String userName);
}
