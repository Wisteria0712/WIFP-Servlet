package com.wisteria.service.impl;

import com.wisteria.mapper.NoteMapper;
import com.wisteria.service.INoteService;

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
}
