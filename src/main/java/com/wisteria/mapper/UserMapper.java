package com.wisteria.mapper;

import com.wisteria.domain.User;
import com.wisteria.util.DBUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserMapper {
    /**
     * 根据用户名和密码查询用户信息
     */
    public User getUserInfo(String userName, String password) {
        String sql = "select * from users where userName = ? and password = ?";
        User user = null;
        try {
            List<Map<String, Object>> resultMap = DBUtil.executeQuery(sql, userName, password);
            if (!resultMap.isEmpty()) {
                user = User.builder()
                        .userName(resultMap.get(0).get("userName").toString())
                        .nickname(resultMap.get(0).get("nickname").toString())
                        .password(resultMap.get(0).get("password").toString())
                        .telephone(resultMap.get(0).get("telephone").toString())
                        .photo(resultMap.get(0).get("photo").toString())
                        .isAuthor(resultMap.get(0).get("isAuthor").toString().charAt(0))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
