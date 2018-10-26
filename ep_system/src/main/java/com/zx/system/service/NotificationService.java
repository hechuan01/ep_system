package com.zx.system.service;

import com.zx.common.enums.TypeEnums;
import com.zx.system.model.SysNotification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Interface description
 *
 * @author V.E.
 * @version 1.0, 17/03/25
 */
@Service
public interface NotificationService {
    List<SysNotification> getUnHandleNotifications(Map map);

    List<SysNotification> getUnHandleNotifications(TypeEnums.NotificationType type, Map map);

    int selectCount(Map map);

    List<SysNotification> selectList(Map map);

    int update(SysNotification sysNotification);
    int deleteBySID(Integer sid, Integer type);
    int deleteList(String ids);

    int deleteList(String ids, String separator);
}


