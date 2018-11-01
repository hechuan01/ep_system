package com.zx.system.service.impl;

import com.zx.common.utils.UUIDUtil;
import com.zx.system.dao.SysRoleDao;
import com.zx.system.dao.SysRolemoduleDao;
import com.zx.system.model.SysRole;
import com.zx.system.model.SysRolemodule;
import com.zx.system.service.RoleService;

import org.apache.commons.lang.StringUtils;
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
    public SysRole selectById(String id) {
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
        if (StringUtils.isNotEmpty(role.getId())) {
            sysRoleDao.update(role);
        } else {
        	String uuid = UUIDUtil.getUUID();
        	role.setId(uuid);
            sysRoleDao.insert(role);
        }
        return role;
    }

    @Override
    public List<SysRole> selectList(Map map) {

        return sysRoleDao.selectList(map);
    }
    
    @Override
    public List<SysRole> getList(Map map) {

        return sysRoleDao.getList(map);
    }

    @Override
    public int updateRoleModules(String roleId, List<SysRolemodule> rmList) {
        sysRolemoduleDao.deleteByRoleId(roleId);
        for (SysRolemodule sysRolemodule : rmList) {
        	String uuid = UUIDUtil.getUUID();
        	sysRolemoduleDao.insert(uuid, sysRolemodule.getRoleid(), sysRolemodule.getMcode());
		}
        return 1;
//        return sysRolemoduleDao.insertBatch(rmList);
    }

}

