package com.wisteria.controller;

import cn.hutool.crypto.SecureUtil;
import com.wisteria.domain.User;
import com.wisteria.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 修改密码
 */
@WebServlet("/user/ChangePasswordServlet.tran")
public class ChangePasswordServlet extends HttpServlet {

    private static final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("change password access");
        HttpSession session = req.getSession();
        String oldPassword = req.getParameter("oldPassword");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        User curUser = (User) session.getAttribute("user");
        if (!curUser.getPassword().equalsIgnoreCase(SecureUtil.md5(oldPassword))) {
            session.setAttribute("msgs", "原密码错误!");
        }
        if (!password.equals(confirmPassword)) {
            session.setAttribute("msgs", "密码与确认密码不一致!");
        }
        curUser.setPassword(SecureUtil.md5(password));
        if (userService.changePassword(curUser)) {
            session.setAttribute("msgs", "修改密码成功!");
            resp.sendRedirect(req.getContextPath() + "/IndexServlet.tran");
        } else {
            session.setAttribute("msgs", "修改密码失败!");
            req.getRequestDispatcher("/user/changePassword.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
