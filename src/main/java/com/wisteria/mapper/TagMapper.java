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
        String sql = "select tagName from tag";
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
}
