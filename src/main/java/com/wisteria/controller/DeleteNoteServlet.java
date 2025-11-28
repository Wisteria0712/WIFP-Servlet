package com.wisteria.controller;

import com.wisteria.service.impl.NoteServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet("/author/DeleteNoteServlet.tran")
public class DeleteNoteServlet extends HttpServlet {
    private static final NoteServiceImpl noteService = new NoteServiceImpl();

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteNoteServlet.doPost access");
        HttpSession session = req.getSession();
        String noteID = req.getParameter("noteID");
        System.out.println("noteID:" + noteID);
        int result = noteService.deleteNoteByID(noteID);
        System.out.println("result:" + result);
        session.removeAttribute("msgs");
        if (result > 0) {
            session.setAttribute("msgs", "删除成功");
        } else {
            session.setAttribute("msgs", "删除失败");
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/FetchAllNoteServlet.tran");
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
