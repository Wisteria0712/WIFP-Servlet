package com.wisteria.controller;

import com.wisteria.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet("/author/EditAuthorInfoServlet.tran")
public class EditAuthorInfoServlet extends HttpServlet {
    @Override
    protected void doGet(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EditAuthorInfoServlet.doGet access");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String userName = user.getUserName();
        String nickName = user.getNickName();
        String telephone = user.getTelephone();
        String brief = user.getBrief();
        req.setAttribute("userName", userName);
        req.setAttribute("nickName", nickName);
        req.setAttribute("telephone", telephone);
        req.setAttribute("brief", brief);
        req.getRequestDispatcher("/IndexServlet.tran?url=/author/changeAuthorInfo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
