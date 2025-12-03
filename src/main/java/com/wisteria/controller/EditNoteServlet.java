package com.wisteria.controller;

import com.wisteria.domain.Note;
import com.wisteria.service.impl.NoteServiceImpl;
import com.wisteria.service.impl.TagServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

@WebServlet("/author/EditNoteServlet.tran")
public class EditNoteServlet extends HttpServlet {
    private static final NoteServiceImpl noteService = new NoteServiceImpl();
    private static final TagServiceImpl tagService = new TagServiceImpl();

    @Override
    protected void doGet(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EditNoteServlet.doGet access");
        String noteID = req.getParameter("noteID");
        Note noteForm = noteService.getNoteByID(noteID);
        req.setAttribute("noteForm", noteForm);
        //请求所有类别列表
        List<String> allCategoryNameList = noteService.fetchAllCategoryNameOnly();
        req.setAttribute("allCategoryNameList", allCategoryNameList);
        //请求所有标签列表
        List<String> allTagNameList = tagService.fetchAllTagName();
        req.setAttribute("allTagNameList", allTagNameList);
        req.getRequestDispatcher("/IndexServlet.tran?url=/author/editNote.jsp").forward(req, resp);
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
