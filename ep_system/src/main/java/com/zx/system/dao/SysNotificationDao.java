package com.zx.system.dao;

import com.zx.system.model.SysNotification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SysUser数据库操作接口
 *
 * @author V.E.
 * @version 1.0, 17/03/13
 */
@Repository("sysNotificationDao")
public interface SysNotificationDao {

    List<SysNotification> getUnHandleNotifications(Map map);

    List<SysNotification> selectList(Map map);

    int selectCount(Map map);
    /**
     * 插入记录
     */
    int insert(SysNotification model);

    /**
     * 更新
     */
    int update(SysNotification model);
    /**
     * 插入多条记录
     */
    int insertBatch(List<SysNotification> sysNotificationList);

    int deleteList(List<Integer> list);

    int deleteBySID(Integer sid, Integer type);
}


