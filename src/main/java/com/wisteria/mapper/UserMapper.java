package com.wisteria.mapper;

import com.wisteria.domain.User;
import com.wisteria.util.DBUtil;
import org.jetbrains.annotations.NotNull;

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
                        .isAuthor(resultMap.get(0).get("isAuthor").toString())
                        .createTime(resultMap.get(0).get("createTime").toString())
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * 根据用户名查找用户信息
     */
    public User findByUserName(String userName) {
        String sql = "select * from users where userName = ?";
        User user = null;
        try {
            List<Map<String, Object>> resultMap = DBUtil.executeQuery(sql, userName);
            if (!resultMap.isEmpty()) {
                user = User.builder()
                        .userName(resultMap.get(0).get("userName").toString())
                        .nickname(resultMap.get(0).get("nickname").toString())
                        .password(resultMap.get(0).get("password").toString())
                        .telephone(resultMap.get(0).get("telephone").toString())
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * 用户注册
     */
    public boolean register(@NotNull User user) {
        String sql = "insert into users(userName,nickname,password,telephone,brief,createtime) values(?,?,?,?,?,?)";
        int line = 0;
        try {
            line = DBUtil.executeUpdate(sql, user.getUserName(), user.getNickname(), user.getPassword(), user.getTelephone(), user.getBrief(), user.getCreateTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return line == 1;
    }

    /**
     * 用户修改密码
     */
    public boolean changePassword(@NotNull User user) {
        String sql = "update users set password = ? where userName = ?";
        int line = 0;
        try {
            line = DBUtil.executeUpdate(sql, user.getPassword(), user.getUserName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return line == 1;
    }

    /**
     * 用户修改头像
     */
    public void changePhoto(@NotNull User user) {
        String sql = "update users set photo = ? where userName = ?";
        try {
            DBUtil.executeUpdate(sql, user.getPhoto(), user.getUserName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
