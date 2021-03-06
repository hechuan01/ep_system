package com.zx.system.service.impl;

import com.zx.common.utils.UUIDUtil;
import com.zx.system.dao.SysModuleDao;
import com.zx.system.model.SysModule;
import com.zx.system.service.ModuleService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.List;

/**
 * 菜单
 *
 * @author V.E.
 * @version 1.0, 17/03/17
 */
@Service("moduleServiceImpl")
@Transactional
public class ModuleServiceImpl implements ModuleService {

    @Resource
    public SysModuleDao sysModuleDao;

    @Override
    public List<SysModule> getList() {
        return sysModuleDao.selectList();
    }

    @Override
    public SysModule selectById(String id) {
        return sysModuleDao.selectById(id);
    }

    @Override
    public SysModule update(SysModule sysModule) {
        if (sysModule.getId() != null && !"".equals(sysModule.getId())) {
            sysModuleDao.update(sysModule);
        } else {
        	sysModule.setId(UUIDUtil.getUUID());
            sysModuleDao.insert(sysModule);
        }

        return sysModule;
    }

    @Override
    public SysModule selectByCode(String code) {
        return sysModuleDao.selectByCode(code);
    }

    @Override
    public List<SysModule> selectByIds(List<String> ids) {
        return sysModuleDao.selectByIds(ids);
    }

    @Override
    public List<SysModule> getSubModules(String parentCode) {
    	Integer length = 0;
    	if(parentCode != null && !"".equals(parentCode)){
    		length = parentCode.length();
    	}
        return sysModuleDao.getSubModules(parentCode,length);
    }


}


