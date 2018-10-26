package com.zx.system.dao;

import com.zx.system.model.SysNotice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by V.E. on 2017/3/5.
 */
@Repository("sysNoticeDao")
public interface SysNoticeDao {

    /**
     * 插入公告
     *
     * @param sysNotice 查询条件
     * @return
     */
    int insert(SysNotice sysNotice);

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
    List<SysNotice> selectList(Map map);

    /**
     * 根据ID查询 SysNotice
     *
     * @param id
     * @return
     */
    SysNotice selectById(int id);

    /**
     * 修改公告
     */
    int update(SysNotice sysNotice);

    /**
     * 删除公告
     */
    int deleteById(int id);

    /**
     * 软删除公告
     */
    int safeDeleteById(int id);

    /* 状态(-1删除0待启动1公告中2已取消3已结束)
    *  定时更新状态方法
    */
    void updateNoticeAndsSurveyState(String date1);
}
