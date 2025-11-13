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

@WebServlet("/FetchAllNoteServlet.tran")
public class FetchAllNoteServlet extends HttpServlet {
    private static final NoteServiceImpl noteService = new NoteServiceImpl();

    @Override
    protected void service(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("fetch all note access");
        req.setAttribute("noteList", noteService.fetchAllNote());
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
