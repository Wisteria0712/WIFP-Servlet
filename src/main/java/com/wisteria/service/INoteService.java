package com.wisteria.service;

import java.util.Map;

public interface INoteService {

    /**
     * 获取Note类别
     */
    Map<String, Integer> fetchNoteCategory();
}
