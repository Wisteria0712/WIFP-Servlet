package com.wisteria.mapper;

import com.wisteria.util.DBUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CommentMapper {
    /**
     * 获取评论
     */
    public List<Map<String, Object>> fetchCommentList(String userName) throws SQLException {
        String sql = "select * from comment where userName = ?";
        return DBUtil.executeQuery(sql, userName);
    }
}
