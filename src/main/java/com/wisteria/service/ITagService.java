package com.wisteria.service;

import java.util.Map;

public interface ITagService {
    /**
     * 获取标签信息
     */
    Map<String, Integer> fetchTagInfo();
}
