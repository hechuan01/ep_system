package com.zx.xt.dao;

import com.zx.xt.model.SearchLog;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：${search_Log数据库操作接口}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-27 14:16
 **/
@Repository("searchLogDao")
public interface SearchLogDao {
    /**
     * 插入记录
     */
    int insert(SearchLog searchLog);

    /**
     * 查询集合
     */
    List<SearchLog> selectList(Map map);

    /**
     * 查询统计
     */

    List<Map<String, Object>> selectCount(Map map);
}
