package com.zx.system.service.impl;

import com.zx.common.enums.RoleType;
import com.zx.system.dao.SysRoleDao;
import com.zx.system.dao.SysUserDao;
import com.zx.system.model.SysRole;
import com.zx.system.model.SysUser;
import com.zx.system.model.SysUserRole;
import com.zx.system.model.UserLogin;
import com.zx.system.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
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
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysRoleDao sysRoleDao;

    @Override
    public boolean deleteById(Integer id) {
        if (id != null) {
            try {
                sysUserDao.deleteById(id.intValue());
            } catch (Exception ex) {
                return false;
            }
        }

        return false;
    }

    /*
     * 缓存: @CacheEvict(value = "userCache", allEntries = true)清缓存
     * 外部指定一个方法清空改"userCache"的所有缓存
     */
    @CacheEvict(
            value = "userCache_getList",
            allEntries = true
    )
    @Override
    public void reload() {
    }

    /**
     * 通过ID查询SYSUser对象
     */
    @CacheEvict(
            value = "userCache_getList",
            allEntries = true
    )
    @Override
    public SysUser selectById(int id) {
        return sysUserDao.selectById(id);
    }

    @Override
    public int selectCount() {
        Map paramMap = new HashMap<String, Integer>();
        paramMap.put("state", 0);
        return sysUserDao.selectCount(paramMap);
    }

    @CacheEvict(
            value = "userCache_getList",
            allEntries = true
    )
    @Override
    public SysUser update(SysUser user) {
        if (user.getId() != null) {
            //修改sys_userroles
            SysUserRole ur = sysUserDao.selectUserRoleByUserId(user.getId());
            if (ur != null) {
                if (ur.getRoleid() != user.getSysRole().getId()) {
                    ur.setRoleid(user.getSysRole().getId());
                    sysUserDao.updateUserRole(ur);
                }
            } else {
                sysUserDao.insertUserRole(user.getId(), user.getSysRole().getId());
            }
            sysUserDao.update(user);
        } else {
            sysUserDao.insert(user);
            //插入用户角色关联表
            sysUserDao.insertUserRole(user.getId(), user.getSysRole().getId());
        }

        return user;
    }

    /*
     * 缓存Demo:
     * 该方法的结果会被缓存到名为"userCache_getList"(注:spring-mvc.xml中配置)的缓存中,下次访问直接从缓存中获取,不再执行方法
     */

    // @Cacheable(value = "userCache_getList")
    @Override
    public List<SysUser> getList(Map map) {
        return sysUserDao.selectList(map);
    }

    /**
     * 登录验证
     *
     * @param loginid
     * @param password
     * @return
     */
    @Override
    public UserLogin login(String loginid, String password) {
        UserLogin loginInfo = new UserLogin();
        SysUser sysUser = sysUserDao.selectUserByLogin(loginid, password);
        if (sysUser != null) {
            loginInfo.setID(sysUser.getId());
            loginInfo.setFullName(sysUser.getFullname());
            loginInfo.setLoginId(sysUser.getLoginid());
            loginInfo.setDeptCode(sysUser.getDeptcode());
            loginInfo.setFullName(sysUser.getFullname());
            loginInfo.setModules(sysUser.getModules());
            loginInfo.setPhone(sysUser.getPhone());
            loginInfo.setEmail(sysUser.getEmail());
            loginInfo.setLoginId(sysUser.getLoginid());
            if (sysUser.getSysRole() != null && sysUser.getSysRole().getRoletype() != null) {
                SysRole sysRole = sysRoleDao.selectById(sysUser.getSysRole().getId());
                if (sysRole != null) {
                    loginInfo.setRole(sysRole);
                    loginInfo.setModules(sysRole.getModules());
                }
                loginInfo.setAdmin(sysUser.getSysRole().getRoletype() == RoleType.超管.getValue());
            }
            if (sysUser.getSysDepartment() != null) {
                loginInfo.setDeptName(sysUser.getSysDepartment().getDeptname());
            }
        }

        return loginInfo;
    }
}


