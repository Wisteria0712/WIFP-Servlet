package com.wisteria.service.impl;

import com.wisteria.domain.User;
import com.wisteria.mapper.UserMapper;
import com.wisteria.service.IUserService;

public class UserServiceImpl implements IUserService {
    private static final UserMapper userMapper = new UserMapper();

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     */
    @Override
    public User login(String userName, String password) {
        return userMapper.getUserInfo(userName, password);
    }

    /**
     * 根据用户名查找用户
     *
     * @param userName
     */
    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public boolean register(User user) {
        return userMapper.register(user);
    }

    /**
     * 用户修改密码
     *
     * @param user
     */
    @Override
    public boolean changePassword(User user) {
        return userMapper.changePassword(user);
    }
}
