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
}
