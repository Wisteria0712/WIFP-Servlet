package com.wisteria.controller;

import cn.hutool.crypto.SecureUtil;
import com.wisteria.domain.User;
import com.wisteria.service.impl.UserServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/LoginServlet.tran")
public class LoginServlet extends HttpServlet {

    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
        String passwordMd5 = SecureUtil.md5(password);
        String autoLogin = req.getParameter("autoLogin");
        User curUser = userService.login(username, passwordMd5);
        if (curUser == null) {
            req.getSession().setAttribute("msgs", "用户名或密码错误");
        } else {
            req.getSession().removeAttribute("msgs");
        }
        req.getSession().setAttribute("user", curUser);
        if (Objects.equals(autoLogin, "on")) {
            resp.addCookie(new Cookie("username", username));
            resp.addCookie(new Cookie("password", password));
        }
        resp.sendRedirect(req.getContextPath() + "/IndexServlet.tran");
        System.out.println("login access");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
