package com.wisteria.mapper;

import com.wisteria.domain.Note;
import com.wisteria.util.DBUtil;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteMapper {
    /**
     * 获取Note类别
     */
    public Map<String, Integer> getNoteCategory() {
        String sql = "select count(categoryName) as count,categoryName from note group by categoryName";
        Map<String, Integer> result = new HashMap<>();
        try {
            List<Map<String, Object>> resultMap = DBUtil.executeQuery(sql);
            for (Map<String, Object> map : resultMap) {
                result.put(map.get("categoryName").toString(), Integer.parseInt(map.get("count").toString()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 获取所有巡检反馈
     */
    public List<Map<String, Object>> fetchAllNote() {
        String sql = "select * from note order by createTime desc";
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result = DBUtil.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 根据ID获取单条Note
     */
    public Note getNoteByID(String noteID) {
        String sql = "select * from note where noteID=?";
        Note note = null;
        try {
            List<Map<String, Object>> result = DBUtil.executeQuery(sql, noteID);
            if (!result.isEmpty()) {
                Map<String, Object> map = result.get(0);
                note = Note.builder()
                        .noteID(Integer.parseInt(map.get("noteID").toString()))
                        .author(map.get("author").toString())
                        .noteTitle(map.get("noteTitle").toString())
                        .noteContent(map.get("noteContent").toString())
                        .visit(Integer.parseInt(map.get("visit").toString()))
                        .categoryName(map.get("categoryName").toString())
                        .createTime(map.get("createTime").toString())
                        .updateTime(map.get("updateTime") == null ? null : map.get("updateTime").toString())
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return note;
    }

    /**
     * 根据ID修改文章阅读量
     */
    public void updateNoteVisitCountByID(String noteID) {
        String sql = "update note set visit=visit+1 where noteID=?";
        try {
            DBUtil.executeUpdate(sql, noteID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有分类名称
     */
    public List<String> fetchAllCategoryNameOnly() {
        String sql = "select distinct categoryName from note";
        List<String> result = new ArrayList<>();
        try {
            List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
            for (Map<String, Object> map : maps) {
                result.add(map.get("categoryName").toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 新增Note
     */
    public int insertNote(@NotNull Note note) {
        String sql = "insert into note(author,noteTitle,noteContent,visit,categoryName) values(?,?,?,?,?)";
        int result;
        try {
            result = DBUtil.executeUpdate(sql, note.getAuthor(), note.getNoteTitle(), note.getNoteContent(), note.getVisit(), note.getCategoryName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 根据ID删除Note
     */
    public int deleteNoteByID(String noteID) {
        String sql = "delete from note where noteID=?";
        int result;
        try {
            result = DBUtil.executeUpdate(sql, noteID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 修改note
     */
    public int updateNote(@NotNull Note note) {
        String sql = "update note set noteTitle=?,noteContent=?,categoryName=?,updateTime=? where noteID=?";
        int result;
        try {
            result = DBUtil.executeUpdate(sql, note.getNoteTitle(), note.getNoteContent(), note.getCategoryName(), note.getUpdateTime(), note.getNoteID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 修改类别名称
     */
    public void changeCategoryName(String oldCategoryName, String newCategoryName) {
        String sql = "update note set categoryName=? where categoryName=?";
        try {
            DBUtil.executeUpdate(sql, newCategoryName, oldCategoryName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据CategoryName获取Note
     */
    public List<Note> fetchAllNoteByCategoryName(String categoryName) {
        String sql = "select * from note where categoryName=?";
        List<Note> result = new ArrayList<>();
        try {
            List<Map<String, Object>> maps = DBUtil.executeQuery(sql, categoryName);
            for (Map<String, Object> map : maps) {
                Note note = Note.builder()
                        .noteID(Integer.parseInt(map.get("noteID").toString()))
                        .author(map.get("author").toString())
                        .noteTitle(map.get("noteTitle").toString())
                        .noteContent(map.get("noteContent").toString())
                        .visit(Integer.parseInt(map.get("visit").toString()))
                        .categoryName(map.get("categoryName").toString())
                        .createTime(map.get("createTime").toString())
                        .updateTime(map.get("updateTime") == null ? null : map.get("updateTime").toString())
                        .build();
                result.add(note);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 根据TagName获取Note
     */
    public List<Note> fetchAllNoteByTagName(String tagName) {
        String sql = "select * from note where noteID in (select noteId from tag where tagName=?)";
        List<Note> result = new ArrayList<>();
        try {
            List<Map<String, Object>> maps = DBUtil.executeQuery(sql, tagName);
            for (Map<String, Object> map : maps) {
                Note note = Note.builder()
                        .noteID(Integer.parseInt(map.get("noteID").toString()))
                        .author(map.get("author").toString())
                        .noteTitle(map.get("noteTitle").toString())
                        .noteContent(map.get("noteContent").toString())
                        .visit(Integer.parseInt(map.get("visit").toString()))
                        .categoryName(map.get("categoryName").toString())
                        .createTime(map.get("createTime").toString())
                        .updateTime(map.get("updateTime") == null ? null : map.get("updateTime").toString())
                        .build();
                result.add(note);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 根据关键词获取Note
     */
    public List<Note> fetchAllNoteByKeyword(String keyword) {
        String sql = "select * from note where noteTitle like '%" + keyword + "%' or noteContent like '%" + keyword + "%' or categoryName like '%" + keyword + "%'";
        List<Note> result = new ArrayList<>();
        try {
            List<Map<String, Object>> maps = DBUtil.executeQuery(sql);
            for (Map<String, Object> map : maps) {
                Note note = Note.builder()
                        .noteID(Integer.parseInt(map.get("noteID").toString()))
                        .author(map.get("author").toString())
                        .noteTitle(map.get("noteTitle").toString())
                        .noteContent(map.get("noteContent").toString())
                        .visit(Integer.parseInt(map.get("visit").toString()))
                        .categoryName(map.get("categoryName").toString())
                        .createTime(map.get("createTime").toString())
                        .updateTime(map.get("updateTime") == null ? null : map.get("updateTime").toString())
                        .build();
                result.add(note);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
