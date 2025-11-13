package com.wisteria.controller;

import com.wisteria.domain.Comment;
import com.wisteria.domain.Note;
import com.wisteria.domain.User;
import com.wisteria.service.impl.CommentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@WebServlet("/user/SaveCommentServlet.tran")
public class SaveCommentServlet extends HttpServlet {
    private static final CommentServiceImpl commentService = new CommentServiceImpl();

    @Override
    protected void doPost(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SaveCommentServlet.tran access");
        HttpSession session = req.getSession();
        User curUser = (User) session.getAttribute("user");
        String userName = curUser.getUserName();
        System.out.println("userName:" + userName);
        Note curNote = (Note) session.getAttribute("note");
        String noteID = curNote.getNoteID().toString();
        System.out.println("noteID:" + noteID);
        String commentTitle = req.getParameter("commentTitle");
        System.out.println("commentTitle:" + commentTitle);
        String commentContent = req.getParameter("commentContent");
        System.out.println("commentContent:" + commentContent);
        String remoteIP = req.getRemoteAddr();
        System.out.println("remoteIP:" + remoteIP);
        Comment comment = Comment.builder()
                .noteId(Integer.parseInt(noteID))
                .userName(userName)
                .commentTitle(commentTitle)
                .commentContent(commentContent)
                .remoteIP(remoteIP)
                .build();
        commentService.insertComment(comment);
        //req.getRequestDispatcher("/ReadNoteServlet.tran?noteID=" + noteID).forward(req, resp);
        resp.sendRedirect(getServletContext().getContextPath() + "/ReadNoteServlet.tran?noteID=" + noteID);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
