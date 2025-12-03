package com.wisteria.controller;

import com.wisteria.service.impl.NoteServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet("/author/ChangeCategoryNameServlet.tran")
public class ChangeCategoryNameServlet extends HttpServlet {
    private static final NoteServiceImpl noteService = new NoteServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    //TODO:目前存在一个问题，虽然重写了doPost方法，但是并不经过
    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ChangeCategoryNameServlet.doPost access");
        String oldCategoryName = req.getParameter("oldCategoryName");
        System.out.println("oldCategoryName=" + oldCategoryName);
        String categoryName = req.getParameter("categoryName");
        System.out.println("categoryName=" + categoryName);
        if (!oldCategoryName.equals(categoryName)) {
            System.out.println("old!=new");
            noteService.changeCategoryName(oldCategoryName, categoryName);
        }
        resp.sendRedirect("www.baidu.com");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
