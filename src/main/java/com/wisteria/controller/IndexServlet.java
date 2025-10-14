package com.wisteria.controller;

import com.wisteria.service.impl.NoteServiceImpl;
import com.wisteria.service.impl.TagServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

@WebServlet("/IndexServlet.tran")
public class IndexServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Integer> categoryNameMap = new NoteServiceImpl().fetchNoteCategory();
        req.setAttribute("categoryNameMap", categoryNameMap);
        Map<String, Integer> tagNameMap = new TagServiceImpl().fetchTagInfo();
        req.setAttribute("tagNameMap", tagNameMap);
        req.getRequestDispatcher("wenote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
