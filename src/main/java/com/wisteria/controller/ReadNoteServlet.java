package com.wisteria.controller;

import com.wisteria.domain.Note;
import com.wisteria.domain.User;
import com.wisteria.domain.vo.CommentVO;
import com.wisteria.service.impl.CommentServiceImpl;
import com.wisteria.service.impl.NoteServiceImpl;
import com.wisteria.service.impl.TagServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

@WebServlet("/ReadNoteServlet.tran")
public class ReadNoteServlet extends HttpServlet {
    private static final NoteServiceImpl noteService = new NoteServiceImpl();
    private static final TagServiceImpl tagService = new TagServiceImpl();
    private static final CommentServiceImpl commentService = new CommentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        String noteID = req.getParameter("noteID");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("noteID:" + noteID);
        Note note = noteService.getNoteByID(noteID);
        System.out.println(note);
        session.setAttribute("note", note);
        List<String> tagNameList = tagService.fetchTagNameByNoteID(noteID);
        tagNameList.forEach(System.out::println);
        session.setAttribute("tagNameList", tagNameList);
        //生成上一条(ID递减)
        Note lastNote = noteService.getLastNoteByID(noteID);
        if (lastNote != null) {
            System.out.println("lastNote:" + lastNote);
        }
        session.setAttribute("lastNote", lastNote);
        //生成下一条(ID递增)
        Note nextNote = noteService.getNextNoteByID(noteID);
        if (nextNote != null) {
            System.out.println("nextNote:" + nextNote);
        }
        session.setAttribute("nextNote", nextNote);
        //设置评论信息
        List<CommentVO> commentVOS = commentService.fetchCommentListByNoteID(noteID);
        if (!(user != null && user.getIsAuthor().equals("Y") && note.getAuthor().equals(user.getUserName()))) {
            noteService.updateNoteVisitCountByID(noteID);
        }
        session.setAttribute("commentList", commentVOS);
        resp.sendRedirect("wenote.jsp?url=readNote.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
