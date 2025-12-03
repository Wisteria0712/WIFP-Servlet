package com.wisteria.controller;

import cn.hutool.core.date.DateUtil;
import com.wisteria.domain.Note;
import com.wisteria.domain.Tag;
import com.wisteria.service.impl.NoteServiceImpl;
import com.wisteria.service.impl.TagServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/author/ChangeNoteServlet.tran")
public class ChangeNoteServlet extends HttpServlet {
    private static final NoteServiceImpl noteService = new NoteServiceImpl();
    private static final TagServiceImpl tagService = new TagServiceImpl();

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ChangeNoteServlet.doPost access");
        String noteID = req.getParameter("noteID");
        System.out.println("noteID:" + noteID);
        String noteTitle = req.getParameter("noteTitle");
        System.out.println("noteTitle:" + noteTitle);
        String categoryName = req.getParameter("categoryName");
        System.out.println("categoryName:" + categoryName);

        String tagNames = req.getParameter("tagNames").replaceAll(" ", "");
        String[] tagNameArray = tagNames.split("#");
        System.out.println(tagNameArray.length);
        System.out.println("tagNames:" + tagNames);
        Arrays.stream(tagNameArray).forEach(System.out::println);

        String noteContent = req.getParameter("noteContent");
        System.out.println("noteContent:" + noteContent);
        Note newNote = Note.builder()
                .noteID(Integer.valueOf(noteID))
                .noteTitle(noteTitle)
                .noteContent(noteContent)
                .categoryName(categoryName)
                .updateTime(DateUtil.now())
                .build();
        //根据noteID获取当前文章的标签，如果一样则不用动，反之只修改不一样的
        List<String> curTagNameList = tagService.fetchTagNameByNoteID(noteID);
        if (curTagNameList.equals(Arrays.asList(tagNameArray))) {
            System.out.println("tagNameList is same");
        } else {
            System.out.println("tagNameList is different");
            //先删除所有的
            tagService.deleteTagByNoteID(noteID);
            //插入新的，使用排他原则
            Arrays.stream(tagNameArray).forEach(tagName -> {
                Tag newTag = Tag.builder()
                        .noteID(noteID)
                        .tagName(tagName)
                        .build();
                tagService.bindNoteTag(newTag);
            });
        }
        System.out.println("newNote:" + newNote);
        int i = noteService.updateNote(newNote);
        if (i > 0) {
            System.out.println("update note success");
            req.getSession().setAttribute("msgs", "修改成功");
        } else {
            System.out.println("update note fail");
            req.getSession().setAttribute("msgs", "修改失败");
        }
        resp.sendRedirect(this.getServletContext().getContextPath() + "/FetchAllNoteServlet.tran");
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
