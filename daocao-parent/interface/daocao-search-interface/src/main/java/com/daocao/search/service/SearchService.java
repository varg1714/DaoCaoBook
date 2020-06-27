package com.daocao.search.service;

import com.daocao.myentity.PageResult;

import java.util.Map;

/**
 * @author varg
 * @date 2020/4/16 19:56
 */
public interface SearchService {

    /**
     * 从solr中查询商品信息
     * @param map
     * @return
     */
    PageResult search(Map map);

}
