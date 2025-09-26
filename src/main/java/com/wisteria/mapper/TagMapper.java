package com.wisteria.mapper;

import com.wisteria.util.DBUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagMapper {
    /**
     * 获取标签信息
     */
    public Map<Integer, String> getTagInfo() {
        String sql = "select tagName,noteID from tag";
        Map<Integer, String> result = new HashMap<>();
        try {
            List<Map<String, Object>> resultMap = DBUtil.executeQuery(sql);
            for (Map<String, Object> map : resultMap) {
                result.put(Integer.parseInt(map.get("noteID").toString()), map.get("tagName").toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
