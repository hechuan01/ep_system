package com.zx.system.dao;

import com.zx.system.model.SysCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 *  数据库操作接口
 * 
 */
@Repository("sysCategoryDao")
public interface SysCategoryDao {

	/**
	 * 插入记录
	 */
	int insert(SysCategory sysCategory);

	/**
	 * 批量插入记录
	 */
	int insertBatch(List<SysCategory> sysCategoryList);

	/**
	 * 根据Id更新
	 */
	 int updateById(SysCategory sysCategory);

	/**
	 * 根据Id删除
	 */
	 int deleteById(Integer id);

	/**
	 * 根据Id获取对象
	 */
	SysCategory selectById(Integer id);

	/**
	 * 根据编码获取其和下级数据
	 * @param ctcode
	 * @return
	 */
	List<SysCategory> selectByctcode(String ctcode);
}
