package com.wisteria.service;

import com.wisteria.domain.Note;

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
}
