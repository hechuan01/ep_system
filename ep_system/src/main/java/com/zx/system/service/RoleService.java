package com.zx.system.service;

import com.zx.system.model.SysRole;
import com.zx.system.model.SysRolemodule;

import java.util.List;
import java.util.Map;

/**
 * Interface description
 *
 * @author V.E.
 * @version 1.0, 17/03/13
 */
public interface RoleService {

    /**
     * 根据ID查询SysRole
     *
     * @param id
     * @return
     */
    SysRole selectById(int id);

    /**
     * 查询SysRole总数
     *
     * @return
     */
    int selectCount();

    /**
     * 更新SysRole
     *
     * @param role
     * @return
     */
    SysRole update(SysRole role);

    /**
     * 查询SysRole分页数据
     *
     * @return
     */
    List<SysRole> selectList(Map map);
    
    List<SysRole> getList(Map map);

    int updateRoleModules(Integer roleId ,List<SysRolemodule> rmList);
}


