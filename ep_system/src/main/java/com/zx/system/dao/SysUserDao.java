package com.zx.system.dao;

import com.zx.system.model.SysRole;
import com.zx.system.model.SysUser;
import com.zx.system.model.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SysUser数据库操作接口
 *
 * @author V.E.
 * @version 1.0, 17/03/13
 */
@Repository("sysUserDao")
public interface SysUserDao {

    /**
     * 插入记录
     *
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 批量插入记录
     *
     * @param sysUserList
     * @return
     */
    int insertBatch(List<SysUser> sysUserList);

    /**
     * 根据Id更新
     *
     * @param sysUser
     * @return
     */
    int update(SysUser sysUser);

    /**
     * 更新用户角色关联表
     *
     * @param sysUserRole
     * @return
     */
    int updateUserRole(SysUserRole sysUserRole);


    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据Id获取对象
     *
     * @param id
     * @return
     */
    SysUser selectById(String id);

    /**
     * 查询分页数据
     *
     * @param map 查询条件
     * @return
     */
    List<SysUser> selectList(Map map);

    /**
     * 查询总条数
     *
     * @param map 查询条件
     * @return
     */
    int selectCount(Map map);

    /**
     * 查询userid相关的角色对象
     *
     * @param id
     * @return
     */
    List<SysRole> selectRoleByUserId(Integer id);

    /**
     * 根据userid查询用户角色关联表数据
     *
     * @param userid
     * @return
     */
    SysUserRole selectUserRoleByUserId(@Param("userid") String userid);

    int insertUserRole(@Param("id") String id, @Param("userId") String userId, @Param("roleId") Integer roleId);

    SysUser selectUserByLogin(@Param("loginid") String loginid, @Param("password") String password);

    List<SysUser> getListByRoleList(List<Integer> list);
}


