package com.wisteria.service.impl;

import com.wisteria.domain.Tag;
import com.wisteria.mapper.TagMapper;
import com.wisteria.service.ITagService;

import java.util.List;
import java.util.Map;

public class TagServiceImpl implements ITagService {
    private static final TagMapper tagMapper = new TagMapper();

    /**
     * 获取标签信息
     */
    @Override
    public Map<String, Integer> fetchTagInfo() {
        return tagMapper.getTagInfo();
    }

    /**
     * 获取所有标签名
     */
    @Override
    public List<String> fetchAllTagName() {
        return tagMapper.fetchAllTagName();
    }

    /**
     * Note与Tag绑定
     */
    @Override
    public void bindNoteTag(Tag tag) {
        tagMapper.bindNoteTag(tag);
    }
}
