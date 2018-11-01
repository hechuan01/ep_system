package com.zx.system.dao;

import com.zx.system.model.SysModule;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据库操作接口
 */
@Repository("sysModuleDao")
public interface SysModuleDao {

    /**
     * 插入记录
     */
    int insert(SysModule sysModule);

    /**
     * 批量插入记录
     */
    int insertBatch(List<SysModule> sysModuleList);

    /**
     * 更新
     */
    int update(SysModule sysModule);

    /**
     * 根据Id删除
     */
    int deleteById(Integer id);

    /**
     * 根据Id获取对象
     */
    SysModule selectById(String id);

    /**
     * 查询菜单所有列表
     */
    List<SysModule> selectList();

    SysModule selectByCode(String code);

    List<SysModule> selectByIds(List<String> ids);

    List<SysModule> getSubModules(@Param("code") String code,@Param("length") Integer length);
}
