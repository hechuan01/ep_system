package com.zx.system.service;

import com.zx.system.model.SysModule;

import java.util.List;

/**
 * Interface description
 *
 *
 * @version        1.0, 17/03/17
 * @author         V.E.
 */
public interface ModuleService {
    List<SysModule> getList();

    SysModule selectById(Integer id);

    SysModule update(SysModule sysModule);

    SysModule selectByCode(String parentCode);

    List<SysModule> selectByIds(List<Integer> ids);

    List<SysModule> getSubModules(String parentCode);
}


