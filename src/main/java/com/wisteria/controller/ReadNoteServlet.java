package com.wisteria.controller;

import com.wisteria.service.impl.NoteServiceImpl;
import com.wisteria.service.impl.TagServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ReadNoteServlet.tran")
public class ReadNoteServlet extends HttpServlet {
    private static final NoteServiceImpl noteService = new NoteServiceImpl();
    private static final TagServiceImpl tagService = new TagServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String noteID = req.getParameter("noteID");
        resp.sendRedirect("wenote.jsp?url=readNote.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
