package com.zx.xt.service.impl;

import com.zx.xt.dao.CmtInfoDao;
import com.zx.xt.model.CmtInfo;
import com.zx.xt.service.CmtInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanght on 2017/3/15.
 */

@Service("cmtInfoService")
public class CmtInfoServiceImpl implements CmtInfoService {

    @Resource(name = "cmtInfoDao")
    private CmtInfoDao cmtInfoDao;

    @Override
    public int insert(CmtInfo cmtInfo) {
        return cmtInfoDao.insert(cmtInfo);
    }

    @Override
    public int deleteById(Integer id) {
        return cmtInfoDao.deleteById(id);
    }

    @Override
    public CmtInfo selectById(Integer id) {
        return cmtInfoDao.selectById(id);
    }

    @Override
    public List<CmtInfo> selectList(Integer targetid,Integer targettype) {
        Map paramMap = new HashMap<String, String>();
        paramMap.put("targetid", targetid);
        paramMap.put("targettype",targettype);
        paramMap.put("orderBy", "ID desc");
        return cmtInfoDao.selectList(paramMap);
    }
}
