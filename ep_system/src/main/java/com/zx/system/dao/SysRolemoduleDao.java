package com.zx.system.dao;

import com.zx.system.model.SysRolemodule;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 *  数据库操作接口
 * 
 */
@Repository("sysRolemoduleDao")
public interface SysRolemoduleDao {

	/**
	 * 插入记录
	 */
	int insert(@Param("id") String id, @Param("roleid") String roleid,@Param("mcode") String mcode);

	/**
	 * 批量插入记录
	 */
	int insertBatch(List<SysRolemodule> sysRolemoduleList);

	/**
	 * 根据Id更新
	 */
	 int updateById(SysRolemodule sysRolemodule);

	/**
	 * 根据Id删除
	 */
	 int deleteById(Integer id);

	/**
	 * 根据Id获取对象
	 */
	SysRolemodule selectById(Integer id);

    int deleteByRoleId(String roleId);
}
