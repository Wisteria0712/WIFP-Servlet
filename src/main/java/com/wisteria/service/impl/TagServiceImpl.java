package com.wisteria.service.impl;

import com.wisteria.mapper.TagMapper;
import com.wisteria.service.ITagService;

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
}
