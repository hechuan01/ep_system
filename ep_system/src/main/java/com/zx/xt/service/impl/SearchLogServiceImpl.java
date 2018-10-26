package com.zx.xt.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import com.zx.xt.model.SearchLog;
import com.zx.xt.dao.SearchLogDao;
import com.zx.xt.service.SearchLogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：${SearchLogService实现类}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-27 14:40
 **/
@Service(value = "searchLogService")
public class SearchLogServiceImpl implements SearchLogService {
    private static Logger logger = LogManager.getLogger(SearchLogServiceImpl.class);
    @Resource(name = "searchLogDao")
    private SearchLogDao searchLogDao;

    @Override
    public SearchLog insert(SearchLog searchLog) {
        try {
            searchLogDao.insert(searchLog);
            return searchLog;
        } catch (Exception ex) {
            logger.error("SearchLogServiceImpl.insert方法异常:" + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<SearchLog> selectList(Map map) {
        try {
            return searchLogDao.selectList(map);
        } catch (Exception ex) {
            logger.error("SearchLogServiceImpl.selectList方法异常:" + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> selectCount(Map map) {
        try {
            return searchLogDao.selectCount(map);
        } catch (Exception ex) {
            logger.error("SearchLogServiceImpl.selectCount方法异常:" + ex.getMessage());
        }
        return null;
    }
}
