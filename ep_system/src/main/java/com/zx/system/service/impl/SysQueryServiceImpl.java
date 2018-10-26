package com.zx.system.service.impl;

import com.zx.system.dao.SysQueryDao;
import com.zx.system.model.SysQuery;
import com.zx.system.service.SysQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 描述：${SysQuery服务类}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-20 18:55
 **/
@Service(value = "sysQueryService")
@Transactional
public class SysQueryServiceImpl implements SysQueryService {
    private static Logger logger = LogManager.getLogger(SysQueryServiceImpl.class);
    @Resource
    private SysQueryDao sysQueryDao;

    @Override
    public SysQuery selectById(int id) {
        try {
            return sysQueryDao.selectById(id);
        } catch (Exception ex) {
            logger.error("SysQueryImpl.selectById方法异常:" + ex.getMessage());
        }
        return null;
    }

    @Override
    public int selectCount(Map map) {
        try {
            return sysQueryDao.selectCount(map);
        } catch (Exception ex) {
            logger.error("SysQueryImpl.selectCount方法异常:" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public SysQuery update(SysQuery sysQuery) {
        try {
            if (sysQuery.getId() != null) {
                sysQueryDao.update(sysQuery);
            } else {
                sysQueryDao.insert(sysQuery);
            }
            return sysQuery;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("SysQueryImpl.update方法异常:" + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<SysQuery> selectList(Map map) {
        try {
            //return sysQueryDao.selectList(sysQuery,pageSize,pageIndex);

            return sysQueryDao.selectList(map);
        } catch (Exception ex) {
            logger.error("SysQueryImpl.selectList方法异常:" + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            int result = sysQueryDao.deleteById(id);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            logger.error("SysQueryServiceImpl.deleteById方法异常:" + ex.getMessage());
        }
        return false;
    }
}
