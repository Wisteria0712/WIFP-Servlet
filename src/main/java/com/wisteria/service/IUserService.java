package com.wisteria.service;

import com.wisteria.domain.User;

public interface IUserService {
    /**
     * 用户登录
     */
    User login(String userName, String password);

    /**
     * 根据用户名查找用户
     */
    User findByUserName(String userName);

    /**
     * 用户注册
     */
    boolean register(User user);

    /**
     * 用户修改密码
     */
    boolean changePassword(User user);

    /**
     * 用户修改头像
     */
    void changePhoto(User user);
}
