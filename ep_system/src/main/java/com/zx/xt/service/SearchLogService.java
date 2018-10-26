package com.zx.xt.service;

import com.zx.xt.model.SearchLog;

import java.util.List;
import java.util.Map;

/**
 * 描述：${search_log Service}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-27 14:38
 **/
public interface SearchLogService {
    /**
     * 添加
     */
    SearchLog insert(SearchLog searchLog);

    /*
    * 查询集合
    */
    List<SearchLog> selectList(Map map);

    List<Map<String, Object>> selectCount(Map map);
}
