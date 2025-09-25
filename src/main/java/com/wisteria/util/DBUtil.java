package com.wisteria.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * MySQL数据库工具类，提供数据库连接管理、CRUD操作和事务处理
 */
public class DBUtil {
    // 数据库连接参数
    private static final String driver;
    private static final String url;
    private static final String username;
    private static final String password;

    // 连接池可以根据需要添加，这里使用简单的单连接方式
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    // 静态代码块加载配置文件
    static {
        try {
            // 加载db.properties配置文件
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");
            Properties props = new Properties();
            props.load(is);

            // 读取配置信息
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");

            // 加载数据库驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            throw new ExceptionInInitializerError("初始化数据库工具类失败：" + e.getMessage());
        }
    }

    /**
     * 私有构造方法，防止实例化
     */
    private DBUtil() {
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接对象
     * @throws SQLException SQL异常
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, username, password);
            threadLocal.set(conn);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null && !conn.isClosed()) {
                conn.close();
                threadLocal.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭Statement
     *
     * @param stmt Statement对象
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭ResultSet
     *
     * @param rs ResultSet对象
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开启事务
     *
     * @throws SQLException SQL异常
     */
    public static void beginTransaction() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            conn.setAutoCommit(false); // 关闭自动提交，开启事务
        }
    }

    /**
     * 提交事务
     *
     * @throws SQLException SQL异常
     */
    public static void commitTransaction() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            conn.commit();
        }
    }

    /**
     * 回滚事务
     *
     * @throws SQLException SQL异常
     */
    public static void rollbackTransaction() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            conn.rollback();
        }
    }

    /**
     * 执行增删改操作（INSERT, UPDATE, DELETE）
     *
     * @param sql    SQL语句
     * @param params 参数数组
     * @return 受影响的行数
     * @throws SQLException SQL异常
     */
    public static int executeUpdate(String sql, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        int rows = 0;

        try {
            pstmt = conn.prepareStatement(sql);
            // 设置参数
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rows = pstmt.executeUpdate();
        } finally {
            closeStatement(pstmt);
        }

        return rows;
    }

    /**
     * 执行查询操作（SELECT）
     *
     * @param sql    SQL语句
     * @param params 参数数组
     * @return 结果集列表，每个元素是一个Map代表一行数据
     * @throws SQLException SQL异常
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> resultList = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            // 设置参数
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 处理结果集
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                resultList.add(row);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }

        return resultList;
    }

    /**
     * 执行单值查询，返回第一行第一列的值
     *
     * @param sql    SQL语句
     * @param params 参数数组
     * @return 第一行第一列的值
     * @throws SQLException SQL异常
     */
    public static Object executeScalar(String sql, Object... params) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Object result = null;

        try {
            pstmt = conn.prepareStatement(sql);
            // 设置参数
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getObject(1);
            }
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }

        return result;
    }
}
