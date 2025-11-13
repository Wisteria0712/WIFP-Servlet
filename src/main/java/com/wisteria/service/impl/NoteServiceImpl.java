package com.wisteria.service.impl;

import com.wisteria.domain.Note;
import com.wisteria.mapper.NoteMapper;
import com.wisteria.service.INoteService;

import java.util.List;
import java.util.Map;

public class NoteServiceImpl implements INoteService {
    private static final NoteMapper noteMapper = new NoteMapper();

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
}
