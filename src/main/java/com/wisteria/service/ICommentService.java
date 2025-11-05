package com.wisteria.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ICommentService {
    /**
     * 获取数据
     */
    List<Map<String, Object>> fetchCommentList(String userName) throws SQLException;
}
