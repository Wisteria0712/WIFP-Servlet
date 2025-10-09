package com.wisteria.service;

import com.wisteria.domain.User;

public interface IUserService {
    /**
     * 用户登录
     */
    User login(String userName, String password);
}
