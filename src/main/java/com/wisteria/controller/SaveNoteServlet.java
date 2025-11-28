package com.wisteria.controller;

import com.wisteria.domain.Note;
import com.wisteria.domain.User;
import com.wisteria.service.impl.NoteServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/author/SaveNoteServlet.tran")
public class SaveNoteServlet extends HttpServlet {
    private static final NoteServiceImpl noteService = new NoteServiceImpl();

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("author add note servlet post access");
        HttpSession session = req.getSession();
        User curUser = (User) session.getAttribute("user");
        String noteTitle = req.getParameter("noteTitle");
        System.out.println("noteTitle:" + noteTitle);
        String categoryName = req.getParameter("categoryName");
        System.out.println("categoryName:" + categoryName);
        String tagNames = req.getParameter("tagNames");
        String[] tagNameArray = tagNames.split(" # ");
        List<String> tagNameList = Arrays.stream(tagNameArray).toList();
        tagNameList.forEach(System.out::println);
        System.out.println("tagNames:" + tagNames);
        String noteContent = req.getParameter("noteContent");
        System.out.println("noteContent:" + noteContent);
        Note newNote = Note.builder()
                .author(curUser.getUserName())
                .noteTitle(noteTitle)
                .noteContent(noteContent)
                .visit(0)
                .categoryName(categoryName)
                .build();
        int result = noteService.insertNote(newNote);
        if (result > 0) {
            System.out.println("insert note success");
            session.removeAttribute("msgs");
            session.setAttribute("msgs", "保存成功");
            resp.sendRedirect(req.getServletContext().getContextPath() + "/IndexServlet.tran");
        } else {
            System.out.println("insert note fail");
            session.removeAttribute("msgs");
            session.setAttribute("msgs", "保存失败");
            resp.sendRedirect(req.getServletContext().getContextPath() + "/IndexServlet.tran");
        }
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
