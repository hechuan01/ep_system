package com.zx.system.service.impl;

import com.zx.system.dao.SysCategoryDao;
import com.zx.system.model.SysCategory;
import com.zx.system.service.SysCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wanght on 2017/3/20.
 */

@Service("sysCategoryService")
@Transactional
public class SysCategoryServiceImpl implements SysCategoryService {

    @Resource
    private SysCategoryDao sysCategoryDao;

    @Override
    public List<SysCategory> selectByctcode(String ctcode) {
        return sysCategoryDao.selectByctcode(ctcode);
    }
}
