package com.wisteria.mapper;

import com.wisteria.util.DBUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteMapper {
    /**
     * 获取Note类别
     */
    public Map<String, Integer> getNoteCategory() {
        String sql = "select count(categoryName) as count,categoryName from note group by categoryName";
        Map<String, Integer> result = new HashMap<>();
        try {
            List<Map<String, Object>> resultMap = DBUtil.executeQuery(sql);
            for (Map<String, Object> map : resultMap) {
                result.put(map.get("categoryName").toString(), Integer.parseInt(map.get("count").toString()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
