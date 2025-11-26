package com.wisteria.controller;

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

@WebServlet("/author/AddNoteServlet.tran")
public class AddNoteServlet extends HttpServlet {

    private static final NoteServiceImpl noteService = new NoteServiceImpl();
    private static final TagServiceImpl tagService = new TagServiceImpl();

    @Override
    protected void service(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("author add note servlet access");
        //请求类别列表
        List<String> categoryNameList = noteService.fetchAllCategoryNameOnly();
        req.setAttribute("categoryNameList", categoryNameList);
        //请求标签列表
        List<String> tagNameList = tagService.fetchAllTagName();
        req.setAttribute("tagNameList", tagNameList);
        req.getRequestDispatcher("/IndexServlet.tran?url=/author/addNote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("author add note servlet post access");
        String noteTitle = req.getParameter("noteTitle");
        System.out.println("noteTitle:" + noteTitle);
        String categoryName = req.getParameter("categoryName");
        System.out.println("categoryName:" + categoryName);
        String tagNames = req.getParameter("tagNames");
        System.out.println("tagNames:" + tagNames);
        String noteContent = req.getParameter("noteContent");
        System.out.println("noteContent:" + noteContent);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
