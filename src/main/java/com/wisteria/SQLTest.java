package com.wisteria;

import com.wisteria.util.DBUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SQLTest {
    public static void main(String[] args) {
        try {
            String sql = "SELECT * FROM users";
            List<Map<String, Object>> results = DBUtil.executeQuery(sql);
            for (Map<String, Object> result : results) {
                System.out.println(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeConnection();
        }
    }
}
