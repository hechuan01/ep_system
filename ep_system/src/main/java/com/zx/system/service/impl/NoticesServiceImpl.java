package com.zx.system.service.impl;


import com.zx.common.enums.*;
import com.zx.common.utils.FileReturn;
import com.zx.common.utils.FileUtil;
import com.zx.common.utils.DateUtil;
import com.zx.system.dao.*;
import com.zx.system.model.*;
import com.zx.system.service.NoticesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * sys_notices Service类
 */
@Service("noticesService")
@Transactional
public class NoticesServiceImpl implements NoticesService {
    @Resource
    private SysNoticeDao sysNoticeDao;
    @Resource
    private SysReceiveDao sysReceiveDao;///公告角色关联表
    @Resource
    private SysAttachmentDao sysAttachmentDao;///附件表
    @Resource
    private SysUserDao sysUserDao;///人员表
    @Resource
    private SysNotificationDao sysNotificationDao;///推送表

    /**
     * 添加公告
     *
     * @param sys_notices
     * @param roleList    公告关联角色信息
     * @return
     */
    public int insert(SysNotice sys_notices, List<Integer> roleList, FileReturn fileReturn, UserLogin loginInfo) {
        Date date = new Date();//当前时间
        ///插入公告
        sys_notices.setCreaterid(loginInfo.getID());
        sys_notices.setCreatetime(date);
        sys_notices.setEndtime(DateUtil.dayEnd(sys_notices.getEndtime()));
        if (sys_notices.getStarttime() != null)
            sys_notices.setState(NoticeOrSurveyState.公告中.getValue());
        else
            sys_notices.setState(NoticeOrSurveyState.已保存.getValue());
        sysNoticeDao.insert(sys_notices);

        if (!fileReturn.getFilePath().equals("") && !fileReturn.getFileName().equals("")) {
            ///插入附件表
            SysAttachment sysAttachment = new SysAttachment();
            sysAttachment.setCreaterid(loginInfo.getID());
            sysAttachment.setState(State.正常.getValue());
            sysAttachment.setCreatetime(date);
            sysAttachment.setAttname(fileReturn.getFileName());
            sysAttachment.setAttpath(fileReturn.getFilePath());
            sysAttachment.setAttType(AttType.系统公告.getValue());
            sysAttachment.setTargetid(sys_notices.getId());
            sysAttachmentDao.insert(sysAttachment);
        }


        ///插入公告角色关联
        List<SysReceive> sysReceiveList = new ArrayList<>();
        for (int id : roleList) {
            SysReceive sysReceive = new SysReceive();
            sysReceive.setRevtype(RevType.公告.getValue());
            sysReceive.setRoleid(id);
            sysReceive.setTargetid(sys_notices.getId());
            sysReceiveList.add(sysReceive);
        }


        ///如果发布状态推送通知
        if (sys_notices.getState().equals(NoticeOrSurveyState.公告中.getValue())) {
            List<SysUser> listUser = sysUserDao.getListByRoleList(roleList);
            List<SysNotification> sysNotificationList = new ArrayList<>();
            for (SysUser user : listUser) {
                SysNotification sysNotification = new SysNotification();
                sysNotification.setCreaterid(loginInfo.getID());
                sysNotification.setCreater(loginInfo.getFullName());
                sysNotification.setState(State.正常.getValue());
                sysNotification.setCreatetime(date);
                sysNotification.setSourceid(sys_notices.getId());
                sysNotification.setTargetkey(user.getId().toString());
                sysNotification.setTargettype(TypeEnums.TargetType.个人.getValue());
                sysNotification.setTitle(sys_notices.getTitle());
                sysNotification.setType(TypeEnums.NotificationType.公告.getValue());
                sysNotificationList.add(sysNotification);
            }
            sysNotificationDao.insertBatch(sysNotificationList);
        }

        return sysReceiveDao.insertList(sysReceiveList);
    }

    /**
     * 查询总条数 int
     *
     * @return
     */
    @Override
    public int selectCount(Map map) {
        return sysNoticeDao.selectCount(map);
    }

    @Override
    public List<SysNotice> getList(Map map) {
        return sysNoticeDao.selectList(map);
    }

    /**
     * 根据ID查询 SysNotice
     *
     * @param id
     * @return
     */
    @Override
    public SysNotice selectById(int id) {
        SysNotice sysNotice = sysNoticeDao.selectById(id);
        return sysNotice;
    }

    /**
     * 根据公告id获取关联角色id
     *
     * @param targetid
     * @param revtype
     * @return
     */
    @Override
    public List<Integer> getRolesByNoticeid(int targetid, int revtype) {
        return sysReceiveDao.getRolesByNoticeOrSurveyId(targetid, revtype);
    }

    /*
         * 修改公告，并关联角色
         *
         * */
    @Override
    public void update(SysNotice sys_notices, List<Integer> roleList, FileReturn fileReturn, String ROOT_PATE, UserLogin loginInfo) {
        Date date = new Date();//当前时间
        ///修改公告
        sys_notices.setEndtime(DateUtil.dayEnd(sys_notices.getEndtime()));
        if (sys_notices.getStarttime() != null)
            sys_notices.setState(NoticeOrSurveyState.公告中.getValue());
        else
            sys_notices.setState(NoticeOrSurveyState.已保存.getValue());
        sysNoticeDao.update(sys_notices);

        ///修改关联文件
        if (!fileReturn.getFileName().equals("") && !fileReturn.getFilePath().equals("")) {
            ///获取关联文件
            List<SysAttachment> sysAttachmentList = sysAttachmentDao.getByTargetidAttType(sys_notices.getId(), AttType.系统公告.getValue());
            SysAttachment sysAttachment = null;
            if (sysAttachmentList.size() > 0) {
                sysAttachment = sysAttachmentList.get(0);
                ///删除原文件
                FileUtil.delete(ROOT_PATE, sysAttachment.getAttpath());
                ///修改文件名和地址
                sysAttachment.setAttname(fileReturn.getFileName());
                sysAttachment.setAttpath(fileReturn.getFilePath());
                sysAttachmentDao.update(sysAttachment);
            } else {///如果原先没有公告则新增公告记录
                ///插入附件表
                sysAttachment = new SysAttachment();
                sysAttachment.setCreaterid(loginInfo.getID());
                sysAttachment.setState(State.正常.getValue());
                sysAttachment.setCreatetime(date);
                sysAttachment.setAttname(fileReturn.getFileName());
                sysAttachment.setAttpath(fileReturn.getFilePath());
                sysAttachment.setAttType(AttType.系统公告.getValue());
                sysAttachment.setTargetid(sys_notices.getId());
                sysAttachmentDao.insert(sysAttachment);
            }
        }

        ///关联新的角色
        List<SysReceive> sysReceiveList = new ArrayList<>();
        for (int id : roleList) {
            SysReceive sysReceive = new SysReceive();
            sysReceive.setRevtype(RevType.公告.getValue());
            sysReceive.setRoleid(id);
            sysReceive.setTargetid(sys_notices.getId());
            sysReceiveList.add(sysReceive);
        }
        ///删除原来角色
        sysReceiveDao.delete(sys_notices.getId(), RevType.公告.getValue());
        ///插入新角色
        sysReceiveDao.insertList(sysReceiveList);
        ///如果发布状态推送通知
        if (sys_notices.getState().equals(NoticeOrSurveyState.公告中.getValue())) {
            List<SysUser> listUser = sysUserDao.getListByRoleList(roleList);
            List<SysNotification> sysNotificationList = new ArrayList<>();
            for (SysUser user : listUser) {
                SysNotification sysNotification = new SysNotification();
                sysNotification.setCreaterid(loginInfo.getID());
                sysNotification.setCreater(loginInfo.getFullName());
                sysNotification.setState(State.正常.getValue());
                sysNotification.setCreatetime(date);
                sysNotification.setSourceid(sys_notices.getId());
                sysNotification.setTargetkey(user.getId().toString());
                sysNotification.setTargettype(TypeEnums.TargetType.个人.getValue());
                sysNotification.setTitle(sys_notices.getTitle());
                sysNotification.setType(TypeEnums.NotificationType.公告.getValue());
                sysNotificationList.add(sysNotification);
            }
            sysNotificationDao.insertBatch(sysNotificationList);
        }
    }

    @Override
    public int deleteById(int id) {
        ///删除公告
        return sysNoticeDao.safeDeleteById(id);
    }

    /*状态(-1删除0待启动1公告中2已取消3已结束)
     *定时更新状态方法
     */
    @Override
    public void updateNoticeAndsSurveyState() {

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = df.format(date);
        sysNoticeDao.updateNoticeAndsSurveyState(date1);
    }

    ///发布公告
    @Override
    public int releaseNoticesByid(int id, UserLogin loginInfo) {
        SysNotice sysNotice = sysNoticeDao.selectById(id);
        sysNotice.setState(NoticeOrSurveyState.公告中.getValue());
        Date date = new Date();
        sysNotice.setStarttime(date);
        ///如果发布状态推送通知
        if (sysNotice.getState().equals(NoticeOrSurveyState.公告中.getValue())) {
            List<Integer> list = sysReceiveDao.getRolesByNoticeOrSurveyId(sysNotice.getId(), RevType.公告.getValue());
            List<SysUser> listUser = sysUserDao.getListByRoleList(list);
            List<SysNotification> sysNotificationList = new ArrayList<>();
            for (SysUser user : listUser) {
                SysNotification sysNotification = new SysNotification();
                sysNotification.setCreaterid(loginInfo.getID());
                sysNotification.setCreater(loginInfo.getFullName());
                sysNotification.setState(State.正常.getValue());
                sysNotification.setCreatetime(date);
                sysNotification.setSourceid(sysNotice.getId());
                sysNotification.setTargetkey(user.getId().toString());
                sysNotification.setTargettype(TypeEnums.TargetType.个人.getValue());
                sysNotification.setTitle(sysNotice.getTitle());
                sysNotification.setType(TypeEnums.NotificationType.公告.getValue());
                sysNotificationList.add(sysNotification);
            }
            sysNotificationDao.insertBatch(sysNotificationList);
        }
        return sysNoticeDao.update(sysNotice);

    }
}


