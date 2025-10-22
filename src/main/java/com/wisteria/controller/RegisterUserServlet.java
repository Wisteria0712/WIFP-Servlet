package com.wisteria.controller;

import cn.hutool.crypto.SecureUtil;
import com.wisteria.domain.User;
import com.wisteria.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/RegisterUserServlet.tran")
public class RegisterUserServlet extends HttpServlet {

    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("register access");
        String userName = req.getParameter("userName");
        String nickName = req.getParameter("nickName");
        String password = req.getParameter("password");
        String passwordMd5 = SecureUtil.md5(password);
        String telephone = req.getParameter("telephone");
        String brief = req.getParameter("brief");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createTime = now.format(formatter);
        User newUser = User.builder()
                .userName(userName)
                .nickname(nickName)
                .password(passwordMd5)
                .telephone(telephone)
                .brief(brief)
                .createTime(createTime)
                .build();
        userService.register(newUser);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
