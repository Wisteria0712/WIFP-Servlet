package com.wisteria.controller;

import com.wisteria.domain.User;
import com.wisteria.service.impl.CommentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 用户评论
 */
@WebServlet("/FetchMyCommentServlet.tran")
public class FetchMyCommentServlet extends HttpServlet {

    private static final CommentServiceImpl commentService = new CommentServiceImpl();

    @Override
    protected void service(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("fetch my comment access");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String userName = user.getUserName();
        System.out.println("userName:" + userName);
        req.setAttribute("commentList", commentService.fetchCommentList(userName));
//        req.getRequestDispatcher("noteList.jsp").forward(req, resp);
        req.getRequestDispatcher("/IndexServlet.tran?url=/noteList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
