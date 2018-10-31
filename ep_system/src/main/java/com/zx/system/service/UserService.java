package com.zx.system.service;

import com.zx.system.model.SysUser;
import com.zx.system.model.UserLogin;

import java.util.List;
import java.util.Map;

/**
 * Interface description
 *
 * @author V.E.
 * @version 1.0, 17/03/13
 */
public interface UserService {

    /**
     * Method description
     *
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * Method description
     */
    void reload();

    /**
     * 根据ID查询SYSUser
     *
     * @param id
     * @return
     */
    SysUser selectById(String id);

    /**
     * 查询总数
     *
     * @return
     */
    int selectCount();

    /**
     * 更新或者插入SysUser对象
     *
     * @param user
     * @return
     */
    SysUser update(SysUser user);


    /**
     * 查询分页数据
     *
     * @param map 查询条件
     * @return
     */
    List<SysUser> getList(Map map);

    UserLogin login(String loginid, String password);
}


