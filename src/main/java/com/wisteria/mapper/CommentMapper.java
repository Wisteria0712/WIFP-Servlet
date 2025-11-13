package com.wisteria.mapper;

import com.wisteria.domain.Comment;
import com.wisteria.util.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentMapper {
    /**
     * 获取评论
     */
    public List<Map<String, Object>> fetchCommentList(String userName) {
        String sql = "select * from comment where userName=?";
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            maps = DBUtil.executeQuery(sql, userName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maps;
    }

    public List<Comment> fetchAllComment(String userName) {
        String sql = "select * from comment where userName=?";
        List<Comment> comments = new ArrayList<>();
        try {
            List<Map<String, Object>> maps = DBUtil.executeQuery(sql, userName);
            for (Map<String, Object> map : maps) {
                Comment build = Comment.builder()
                        .commentId(Integer.parseInt(map.get("commentID").toString()))
                        .remoteIP(map.get("remoteIP").toString())
                        .commentTitle(map.get("commentTitle").toString())
                        .createTime(map.get("createTime").toString())
                        .noteId(Integer.parseInt(map.get("noteID").toString()))
                        .commentContent(map.get("commentContent").toString())
                        .userName(map.get("userName").toString())
                        .build();
                comments.add(build);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
}
