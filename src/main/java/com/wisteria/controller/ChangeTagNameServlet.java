package com.wisteria.controller;

import com.wisteria.service.impl.TagServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet("/author/ChangeTagNameServlet.tran")
public class ChangeTagNameServlet extends HttpServlet {
    private static final TagServiceImpl tagService = new TagServiceImpl();

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ChangeTagNameServlet.doPost access");
        String oldTagName = req.getParameter("oldTagName");
        System.out.println("oldTagName=" + oldTagName);
        String tagName = req.getParameter("tagName");
        System.out.println("tagName=" + tagName);
        if (!oldTagName.equals(tagName)) {
            System.out.println("oldTagName!=tagName");
            tagService.changeTagName(oldTagName, tagName);
        }
        resp.sendRedirect(req.getContextPath() + "/IndexServlet.tran");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
