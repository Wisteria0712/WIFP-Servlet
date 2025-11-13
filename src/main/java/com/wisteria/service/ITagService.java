package com.wisteria.service;

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
}
