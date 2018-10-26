package com.zx.system.service.impl;

import com.zx.system.dao.SysRoleDao;
import com.zx.system.dao.SysRolemoduleDao;
import com.zx.system.model.SysRole;
import com.zx.system.model.SysRolemodule;
import com.zx.system.service.RoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author V.E.
 * @version 1.0, 17/03/13
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    /**
     * Field description
     */
    @Resource
    private SysRoleDao sysRoleDao;
    @Resource
    private SysRolemoduleDao sysRolemoduleDao;

    @Override
    public SysRole selectById(int id) {
        return sysRoleDao.selectById(id);
    }

    @Override
    public int selectCount() {
        Map paramMap = new HashMap<String, Integer>();

        paramMap.put("state", 0);

        return sysRoleDao.selectCount(paramMap);
    }

    @Override
    public SysRole update(SysRole role) {
        if (role.getId() != null) {
            sysRoleDao.update(role);
        } else {
            sysRoleDao.insert(role);
        }
        return role;
    }

    @Override
    public List<SysRole> getList(Map map) {

        return sysRoleDao.selectList(map);
    }

    @Override
    public int updateRoleModules(Integer roleId, List<SysRolemodule> rmList) {
        sysRolemoduleDao.deleteByRoleId(roleId);
        return sysRolemoduleDao.insertBatch(rmList);
    }

}

