package com.wisteria.service;

import com.wisteria.domain.Tag;

import java.util.List;
import java.util.Map;

public interface ITagService {
    /**
     * 获取标签信息
     */
    Map<String, Integer> fetchTagInfo();

    /**
     * 获取所有标签名
     */
    List<String> fetchAllTagName();

    /**
     * Note与Tag绑定
     */
    void bindNoteTag(Tag tag);

    /**
     * 根据NoteID删除对应的标签
     */
    void deleteTagByNoteID(String noteID);
}
