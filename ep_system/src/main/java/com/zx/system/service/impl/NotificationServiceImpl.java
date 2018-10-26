package com.zx.system.service.impl;

import com.zx.common.enums.TypeEnums;
import com.zx.system.dao.SysNotificationDao;
import com.zx.system.model.SysNotification;
import com.zx.system.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author V.E.
 * @version 1.0, 17/03/25
 */
@Service("notificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService {
    @Resource
    private SysNotificationDao sysNotificationDao;

    @Override
    public List<SysNotification> getUnHandleNotifications(Map map) {
        return sysNotificationDao.selectList(map);
    }

    @Override
    public List<SysNotification> getUnHandleNotifications(TypeEnums.NotificationType type, Map map) {
        map.put("type", type.getValue());
        return sysNotificationDao.selectList(map);
    }

    @Override
    public int selectCount(Map map) {
        return sysNotificationDao.selectCount(map);
    }

    @Override
    public List<SysNotification> selectList(Map map) {
        return sysNotificationDao.selectList(map);
    }

    @Override
    public int update(SysNotification sysNotification) {
        if (sysNotification.getId() != null) {
            return sysNotificationDao.update(sysNotification);
        } else {
            return sysNotificationDao.insert(sysNotification);
        }
    }

    @Override
    public int deleteBySID(Integer sid, Integer type) {
        return sysNotificationDao.deleteBySID(sid, type);
    }

    @Override
    public int deleteList(String ids) {
        return deleteList(ids, ",");
    }

    @Override
    public int deleteList(String ids, String separator) {
        String[] stringList = ids.split(separator);
        List<Integer> list = new ArrayList<>();
        for (String item : stringList) {
            try {
                int i = Integer.parseInt(item);
                list.add(i);
            } catch (Exception ex) {

            }
        }
        return sysNotificationDao.deleteList(list);
    }
}


