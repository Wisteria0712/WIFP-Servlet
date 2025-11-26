package com.wisteria.controller;

import com.wisteria.domain.Note;
import com.wisteria.service.impl.NoteServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

@WebServlet("/FetchAllNoteByTagNameServlet.tran")
public class FetchAllNoteByTagNameServlet extends HttpServlet {

    private static final NoteServiceImpl noteService = new NoteServiceImpl();

    @Override
    protected void service(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("fetch all note by tag name access");
        String tagName = req.getParameter("tagName");
        System.out.println("tagName:" + tagName);
        List<Note> notes = noteService.fetchAllNoteByTagName(tagName);
        req.setAttribute("noteList", notes);
        req.getRequestDispatcher("/IndexServlet.tran?url=/noteList.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
