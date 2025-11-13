package com.wisteria.mapper;

import com.wisteria.domain.Comment;
import com.wisteria.util.DBUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentMapper {
    /**
     * 根据文章ID获取文章评论
     */
    public List<Comment> fetchCommentListByNoteID(String noteID) {
        String sql = "select * from comment where noteID=?";
        List<Comment> comments = new ArrayList<>();
        try {
            List<Map<String, Object>> maps = DBUtil.executeQuery(sql, noteID);
            for (Map<String, Object> map : maps) {
                Comment build = Comment.builder()
                        .commentId(Integer.parseInt(map.get("commentID") == null ? String.valueOf(0) : map.get("commentID").toString()))
                        .remoteIP(map.get("remoteIP") == null ? null : map.get("remoteIP").toString())
                        .commentTitle(map.get("commentTitle") == null ? null : map.get("commentTitle").toString())
                        .createTime(map.get("createTime") == null ? null : map.get("createTime").toString())
                        .noteId(Integer.parseInt(map.get("noteID") == null ? String.valueOf(0) : map.get("noteID").toString()))
                        .commentContent(map.get("commentContent") == null ? null : map.get("commentContent").toString())
                        .userName(map.get("userName") == null ? null : map.get("userName").toString())
                        .build();
                comments.add(build);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
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

    /**
     * 新增评论
     */
    public void insertComment(@NotNull Comment comment) {
        String sql = "insert into comment(noteID,userName,commentTitle,commentContent,remoteIP) values(?,?,?,?,?)";
        try {
            DBUtil.executeUpdate(sql, comment.getNoteId(), comment.getUserName(), comment.getCommentTitle(), comment.getCommentContent(), comment.getRemoteIP());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
