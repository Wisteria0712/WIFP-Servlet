package com.wisteria.mapper;

import com.wisteria.domain.Tag;
import com.wisteria.util.DBUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagMapper {
    /**
     * 获取标签信息
     */
    public Map<String, Integer> getTagInfo() {
        String sql = "select tagName,count(*) as tagNum from tag group by tagName";
        Map<String, Integer> result = new HashMap<>();
        try {
            List<Map<String, Object>> resultMap = DBUtil.executeQuery(sql);
            for (Map<String, Object> map : resultMap) {
                result.put(map.get("tagName").toString(), Integer.parseInt(map.get("tagNum").toString()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 获取所有标签名
     */
    public List<String> fetchAllTagName() {
        String sql = "select distinct tagName from tag";
        List<String> result = new ArrayList<>();
        try {
            List<Map<String, Object>> resultMap = DBUtil.executeQuery(sql);
            for (Map<String, Object> map : resultMap) {
                result.add(map.get("tagName").toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Note与Tag绑定
     */
    public void bindNoteTag(@NotNull Tag tag) {
        String sql = "insert into tag(tagName,noteID) values(?,?)";
        try {
            DBUtil.executeUpdate(sql, tag.getTagName(), tag.getNoteID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据NoteID删除对应的标签
     */
    public void deleteTagByNoteID(String noteID) {
        String sql = "delete from tag where noteID = ?";
        try {
            DBUtil.executeUpdate(sql, noteID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据noteID获取对应的标签
     */
    public List<String> fetchTagNameByNoteID(String noteID) {
        String sql = "select tagName from tag where noteID = ?";
        List<String> result = new ArrayList<>();
        try {
            List<Map<String, Object>> resultMap = DBUtil.executeQuery(sql, noteID);
            for (Map<String, Object> map : resultMap) {
                result.add(map.get("tagName").toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 修改TagName
     */
    public void changeTagName(String oldTagName, String newTagName) {
        String sql = "update tag set tagName=? where tagName=?";
        try {
            DBUtil.executeUpdate(sql, newTagName, oldTagName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
