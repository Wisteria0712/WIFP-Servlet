package com.wisteria;

import com.wisteria.util.DBUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * DBUtil工具类的使用示例
 */
public class DBUtilTest {
    public static void main(String[] args) {
        testQuery();
        testUpdate();
    }
    
    /**
     * 测试查询操作
     */
    public static void testQuery() {
        try {
            String sql = "SELECT * FROM users WHERE age > ?";
            List<Map<String, Object>> result = DBUtil.executeQuery(sql, 18);
            
            System.out.println("查询结果：");
            for (Map<String, Object> row : result) {
                System.out.println(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
    }
    
    /**
     * 测试更新操作（带事务）
     */
    public static void testUpdate() {
        try {
            // 开启事务
            DBUtil.beginTransaction();
            
            // 执行更新操作
            String insertSql = "INSERT INTO users (name, age, email) VALUES (?, ?, ?)";
            int insertRows = DBUtil.executeUpdate(insertSql, "张三", 25, "zhangsan@example.com");
            System.out.println("插入了 " + insertRows + " 行数据");
            
            String updateSql = "UPDATE users SET age = ? WHERE name = ?";
            int updateRows = DBUtil.executeUpdate(updateSql, 26, "张三");
            System.out.println("更新了 " + updateRows + " 行数据");
            
            // 提交事务
            DBUtil.commitTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 发生异常，回滚事务
                DBUtil.rollbackTransaction();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DBUtil.closeConnection();
        }
    }
}
