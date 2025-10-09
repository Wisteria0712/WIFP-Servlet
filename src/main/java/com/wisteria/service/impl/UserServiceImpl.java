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
}
