package com.zx.system.dao;

import com.zx.system.model.SysRole;

import com.zx.system.model.SysRolemodule;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SysRole 数据操作接口
 *
 * @author V.E.
 * @version 1.0, 17/03/13
 */
@Repository("sysRoleDao")
public interface SysRoleDao {

    /**
     * 插入记录
     *
     * @param sysRole
     * @return
     */
    int insert(SysRole sysRole);

    /**
     * 批量插入记录
     *
     * @param sysRoleList
     * @return
     */
    int insertBatch(List<SysRole> sysRoleList);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据Id获取对象
     *
     * @param id 对象主键
     * @return
     */
    SysRole selectById(String id);

    /**
     * 查询符合条件的数据量
     *
     * @param map 查询条件
     * @return
     */
    int selectCount(Map map);

    /**
     * Method description
     *
     * @param role 更新SysRole
     * @return
     */
    int update(SysRole role);

    /**
     * 根据条件查询数据
     *
     * @param map 查询条件
     * @return
     */
    List<SysRole> selectList(Map map);

    /**
     * 查询userid相关的角色对象
     *
     * @param id
     * @return
     */
    List<SysRole> selectRoleByUserId(Integer id);

    /**
     * 查询角色
     * @return
     */
    List<SysRole> selectRoles();


    void updateRoleModules(List<SysRolemodule> rmList);
    
    List<SysRole> getList(Map map);
}


