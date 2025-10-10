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
}
