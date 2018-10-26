package com.zx.system.dao;

import com.zx.system.model.SysSurvey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by V.E. on 2017/3/5.
 */
@Repository("sysSurveyDao")
public interface SysSurveyDao {

    /**
     * 插入公告
     *
     * @param sysSurvey 查询条件
     * @return
     */
    int insert(SysSurvey sysSurvey);

    /**
     * 查询总条数
     *
     * @param map 查询条件
     * @return
     */
    int selectCount(Map map);

    /**
     * 查询分页数据
     *
     * @param map 查询条件
     * @return
     */
    List<SysSurvey> selectList(Map map);

    /**
     * 根据ID查询 SysSurvey
     *
     * @param id
     * @return
     */
    SysSurvey selectById(int id);

    /**
     * 修改公告
     */
    int update(SysSurvey sysSurvey);
    /**
     * 删除公告
     */
   int deleteById(int id);

    /**
     * 软删除公告
     */
    int safeDeleteById(int id);
    SysSurvey selectLast();
}
