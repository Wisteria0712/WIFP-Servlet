package com.wisteria.controller;

import com.wisteria.service.impl.TagServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

@WebServlet("/author/FetchAllTagNameServlet.tran")
public class FetchAllTagNameServlet extends HttpServlet {
    private static final TagServiceImpl tagService = new TagServiceImpl();

    @Override
    protected void doGet(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FetchAllTagNameServlet.doGet access");
        HttpSession session = req.getSession();
        List<String> tagNameList = tagService.fetchAllTagName();
        session.setAttribute("tagNameList", tagNameList);
        resp.sendRedirect(req.getContextPath() + "/IndexServlet.tran?url=/author/tagNameList.jsp");
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
