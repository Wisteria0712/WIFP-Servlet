package com.wisteria.service;

import com.wisteria.domain.Note;
import com.wisteria.domain.Tag;

import java.util.List;
import java.util.Map;

public interface INoteService {

    /**
     * 获取Note类别
     */
    Map<String, Integer> fetchNoteCategory();

    /**
     * 获取所有巡检反馈
     */
    List<Map<String, Object>> fetchAllNote();

    /**
     * 根据ID获取单条Note
     */
    Note getNoteByID(String noteID);

    /**
     * 根据当前ID获取上一条Note
     */
    Note getLastNoteByID(String noteID);

    /**
     * 根据当前ID获取下一条Note
     */
    Note getNextNoteByID(String noteID);

    /**
     * 根据ID修改ydl
     */
    void updateNoteVisitCountByID(String noteID);

    /**
     * 通过TagName获取Note
     */
    List<Note> fetchAllNoteByTagName(String tagName);

    /**
     * 获取所有类别名称
     */
    List<String> fetchAllCategoryNameOnly();

    /**
     * 新增Note
     */
    int insertNote(Note note);
}
