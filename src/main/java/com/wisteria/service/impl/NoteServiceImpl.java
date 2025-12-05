package com.wisteria.service.impl;

import com.wisteria.domain.Note;
import com.wisteria.mapper.CommentMapper;
import com.wisteria.mapper.NoteMapper;
import com.wisteria.mapper.TagMapper;
import com.wisteria.service.INoteService;

import java.util.List;
import java.util.Map;

public class NoteServiceImpl implements INoteService {
    private static final NoteMapper noteMapper = new NoteMapper();
    private static final CommentMapper commentMapper = new CommentMapper();
    private static final TagMapper tagMapper = new TagMapper();

    /**
     * 获取Note类别
     */
    @Override
    public Map<String, Integer> fetchNoteCategory() {
        return noteMapper.getNoteCategory();
    }

    /**
     * 获取所有巡检反馈
     */
    @Override
    public List<Map<String, Object>> fetchAllNote() {
        return noteMapper.fetchAllNote();
    }

    /**
     * 根据ID获取单条Note
     */
    @Override
    public Note getNoteByID(String noteID) {
        return noteMapper.getNoteByID(noteID);
    }

    /**
     * 根据ID修改ydl
     */
    @Override
    public void updateNoteVisitCountByID(String noteID) {
        noteMapper.updateNoteVisitCountByID(noteID);
    }

    /**
     * 根据当前ID获取上一条Note
     */
    @Override
    public Note getLastNoteByID(String noteID) {
        int curNoteID = Integer.parseInt(noteID) - 1;
        while (curNoteID != 0) {
            Note note = noteMapper.getNoteByID(curNoteID + "");
            if (note != null) {
                return note;
            }
            curNoteID--;
        }
        return null;
    }

    /**
     * 根据当前ID获取下一条Note
     */
    @Override
    public Note getNextNoteByID(String noteID) {
        int curNoteID = Integer.parseInt(noteID) + 1;
        while (curNoteID != 0) {
            Note note = noteMapper.getNoteByID(curNoteID + "");
            if (note != null) {
                return note;
            }
            curNoteID++;
        }
        return null;
    }

    /**
     * 通过TagName获取Note
     */
    @Override
    public List<Note> fetchAllNoteByTagName(String tagName) {
        return List.of();
    }

    /**
     * 获取所有类别名称
     */
    @Override
    public List<String> fetchAllCategoryNameOnly() {
        return noteMapper.fetchAllCategoryNameOnly();
    }

    /**
     * 新增Note
     */
    @Override
    public int insertNote(Note note) {
        return noteMapper.insertNote(note);
    }

    /**
     * 根据ID删除Note
     */
    @Override
    public int deleteNoteByID(String noteID) {
        //删除指定ID的Note下对应的评论
        commentMapper.deleteCommentByNoteID(noteID);
        //删除指定ID的Note下对应的Tag
        tagMapper.deleteTagByNoteID(noteID);
        return noteMapper.deleteNoteByID(noteID);
    }

    /**
     * 修改Note
     */
    @Override
    public int updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    /**
     * 修改类别名称
     */
    @Override
    public void changeCategoryName(String oldCategoryName, String newCategoryName) {
        noteMapper.changeCategoryName(oldCategoryName, newCategoryName);
    }

    /**
     * 根据CategoryName获取Note
     */
    @Override
    public List<Note> fetchAllNoteByCategoryName(String categoryName) {
        return noteMapper.fetchAllNoteByCategoryName(categoryName);
    }
}
