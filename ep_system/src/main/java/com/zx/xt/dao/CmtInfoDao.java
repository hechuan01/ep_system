package com.zx.xt.dao;

import com.zx.xt.model.CmtInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 *  数据库操作接口
 * 
 */
@Repository("cmtInfoDao")
public interface CmtInfoDao {

	/**
	 * 插入记录
	 */
	int insert(CmtInfo cmtInfo);

	/**
	 * 根据Id删除
	 */
	 int deleteById(Integer id);

	/**
	 * 根据Id获取对象
	 */
	CmtInfo selectById(Integer id);

	/**
	 * 根据Id和类型查询评论列表
	 */
	List<CmtInfo> selectList(Map map);

	/**
	 * 获取某被评论对象的平均star
	 * @param targetid
	 * @return
	 */
	int getAverageStar(Integer targetid);
}
