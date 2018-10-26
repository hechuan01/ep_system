package com.zx.system.service.impl;


import com.zx.common.enums.*;
import com.zx.common.utils.FileReturn;
import com.zx.common.utils.FileUtil;
import com.zx.common.utils.TimeHelpUtil;
import com.zx.system.dao.*;
import com.zx.system.model.*;
import com.zx.system.service.SurveysService;
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
 * SurveysService Service类
 */
@Service("surveysService")
@Transactional
public class SurveysServiceImpl implements SurveysService {


    @Resource
    private SysSurveyDao sysSurveyDao;
    @Resource
    private SysReceiveDao sysReceiveDao;///调研角色关联表
    @Resource
    private SysAttachmentDao sysAttachmentDao;///附件表
    @Resource
    private SysUserDao sysUserDao;///人员表
    @Resource
    private SysNotificationDao sysNotificationDao;///推送表
    /**
     * 添加调研
     *
     * @param sysSurvey
     * @param roleList  调研关联角色信息
     * @return
     */
    public int insert(SysSurvey sysSurvey, List<Integer> roleList, FileReturn fileReturn, UserLogin loginInfo) {
        Date date = new Date();

        ///插入调研
        sysSurvey.setCreaterid(loginInfo.getID());
        sysSurvey.setCreatetime(date);
        sysSurvey.setSvnumber(createSurveyNumber());


        sysSurvey.setEndtime(TimeHelpUtil.dayEnd(sysSurvey.getEndtime()));
        if (sysSurvey.getStarttime() != null)
            sysSurvey.setState(NoticeOrSurveyState.公告中.getValue());
        else
            sysSurvey.setState(NoticeOrSurveyState.已保存.getValue());


        sysSurveyDao.insert(sysSurvey);


        if (!fileReturn.getFilePath().equals("") && !fileReturn.getFileName().equals("")) {
            ///插入附件表
            SysAttachment sysAttachment = new SysAttachment();
            sysAttachment.setCreaterid(loginInfo.getID());
            sysAttachment.setState(State.正常.getValue());
            sysAttachment.setCreatetime(date);
            sysAttachment.setAttname(fileReturn.getFileName());
            sysAttachment.setAttpath(fileReturn.getFilePath());
            sysAttachment.setAttType(AttType.系统调研.getValue());
            sysAttachment.setTargetid(sysSurvey.getId());
            sysAttachmentDao.insert(sysAttachment);
        }

        ///插入调研角色关联
        List<SysReceive> sysReceiveList = new ArrayList<>();
        for (int id : roleList) {
            SysReceive sysReceive = new SysReceive();
            sysReceive.setRevtype(RevType.调研.getValue());
            sysReceive.setRoleid(id);
            sysReceive.setTargetid(sysSurvey.getId());
            sysReceiveList.add(sysReceive);
        }

        ///如果发布状态推送通知
        if (sysSurvey.getState().equals(NoticeOrSurveyState.公告中.getValue())) {
            List<SysUser> listUser = sysUserDao.getListByRoleList(roleList);
            List<SysNotification> sysNotificationList = new ArrayList<>();
            for (SysUser user : listUser) {
                SysNotification sysNotification = new SysNotification();
                sysNotification.setCreaterid(loginInfo.getID());
                sysNotification.setCreater(loginInfo.getFullName());
                sysNotification.setState(State.正常.getValue());
                sysNotification.setCreatetime(date);
                sysNotification.setSourceid(sysSurvey.getId());
                sysNotification.setTargetkey(user.getId().toString());
                sysNotification.setTargettype(TypeEnums.TargetType.个人.getValue());
                sysNotification.setTitle(sysSurvey.getTitle());
                sysNotification.setType(TypeEnums.NotificationType.调研.getValue());
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
        return sysSurveyDao.selectCount(map);
    }

    @Override
    public List<SysSurvey> getList(Map map) {
        return sysSurveyDao.selectList(map);
    }

    /**
     * 根据ID查询 SysSurvey
     *
     * @param id
     * @return
     */
    @Override
    public SysSurvey selectById(int id) {
        SysSurvey sysSurvey = sysSurveyDao.selectById(id);
        return sysSurvey;
    }

    /**
     * 根据调研id获取关联角色id
     *
     * @param targetid
     * @param revtype
     * @return
     */
    @Override
    public List<Integer> getRolesByNoticeOrSurveyId(int targetid, int revtype) {
        return sysReceiveDao.getRolesByNoticeOrSurveyId(targetid, revtype);
    }


    /*
             * 修改调研，并关联角色
             *
             * */
    @Override
    public void update(SysSurvey sysSurvey, List<Integer> roleList, FileReturn fileReturn, String ROOT_PATE, UserLogin loginInfo) {
        Date date = new Date();//当前时间
        ///修改调研
        ///修改公告
        sysSurvey.setEndtime(TimeHelpUtil.dayEnd(sysSurvey.getEndtime()));
        if (sysSurvey.getStarttime() != null)
            sysSurvey.setState(NoticeOrSurveyState.公告中.getValue());
        else
            sysSurvey.setState(NoticeOrSurveyState.已保存.getValue());
        sysSurveyDao.update(sysSurvey);

        ///修改关联文件
        if (!fileReturn.getFileName().equals("") && !fileReturn.getFilePath().equals("")) {
            ///获取关联文件
            List<SysAttachment> sysAttachmentList = sysAttachmentDao.getByTargetidAttType(sysSurvey.getId(), AttType.系统调研.getValue());
            SysAttachment sysAttachment = null;
            if (sysAttachmentList.size() > 0) {
                sysAttachment = sysAttachmentList.get(0);
                ///删除原文件
                FileUtil.delete(ROOT_PATE, sysAttachment.getAttpath());
                ///修改文件名和地址
                sysAttachment.setAttname(fileReturn.getFileName());
                sysAttachment.setAttpath(fileReturn.getFilePath());
                sysAttachmentDao.update(sysAttachment);
            } else {///如果原先没有调研则新增调研记录
                ///插入附件表
                sysAttachment = new SysAttachment();
                sysAttachment.setCreaterid(loginInfo.getID());
                sysAttachment.setState(State.正常.getValue());
                sysAttachment.setCreatetime(date);
                sysAttachment.setAttname(fileReturn.getFileName());
                sysAttachment.setAttpath(fileReturn.getFilePath());
                sysAttachment.setAttType(AttType.系统调研.getValue());
                sysAttachment.setTargetid(sysSurvey.getId());
                sysAttachmentDao.insert(sysAttachment);

            }
        }

        ///关联新的角色
        List<SysReceive> sysReceiveList = new ArrayList<>();
        for (int id : roleList) {
            SysReceive sysReceive = new SysReceive();
            sysReceive.setRevtype(RevType.调研.getValue());
            sysReceive.setRoleid(id);
            sysReceive.setTargetid(sysSurvey.getId());
            sysReceiveList.add(sysReceive);
        }
        ///删除原来角色
        sysReceiveDao.delete(sysSurvey.getId(), RevType.调研.getValue());
        ///插入新角色
        sysReceiveDao.insertList(sysReceiveList);
        ///如果发布状态推送通知
        if (sysSurvey.getState().equals(NoticeOrSurveyState.公告中.getValue())) {
            List<SysUser> listUser = sysUserDao.getListByRoleList(roleList);
            List<SysNotification> sysNotificationList = new ArrayList<>();
            for (SysUser user : listUser) {
                SysNotification sysNotification = new SysNotification();
                sysNotification.setCreaterid(loginInfo.getID());
                sysNotification.setCreater(loginInfo.getFullName());
                sysNotification.setState(State.正常.getValue());
                sysNotification.setCreatetime(date);
                sysNotification.setSourceid(sysSurvey.getId());
                sysNotification.setTargetkey(user.getId().toString());
                sysNotification.setTargettype(TypeEnums.TargetType.个人.getValue());
                sysNotification.setTitle(sysSurvey.getTitle());
                sysNotification.setType(TypeEnums.NotificationType.公告.getValue());
                sysNotificationList.add(sysNotification);
            }
            sysNotificationDao.insertBatch(sysNotificationList);
        }
    }

    @Override
    public int deleteById(int id) {
       /* ///删除调研关联角色
        sysReceiveDao.delete(id, RevType.调研.getValue());
        ///获取调研关联附件表
        List<SysAttachment> sysAttachmentList = sysAttachmentDao.getByTargetidAttType(id, AttType.系统调研.getValue());
        ///删除关联附件
        for (SysAttachment sysAttachment : sysAttachmentList) {
            FileUtil.delete(sysAttachment.getAttpath());
        }
        ///删除关联附件表
        sysAttachmentDao.delByTargetidAttType(id,AttType.系统调研.getValue());
        ///删除公告
        return sysSurveyDao.deleteById(id);*/
        ///删除公告
        return sysSurveyDao.safeDeleteById(id);
    }

    //发布调研
    @Override
    public int releaseSurveysByid(int id,UserLogin loginInfo) {
        SysSurvey sysSurvey = sysSurveyDao.selectById(id);
        sysSurvey.setState(NoticeOrSurveyState.公告中.getValue());
        Date date=new Date();
        sysSurvey.setStarttime(date);
        ///如果发布状态推送通知
        if (sysSurvey.getState().equals(NoticeOrSurveyState.公告中.getValue())) {
            List<Integer> list = sysReceiveDao.getRolesByNoticeOrSurveyId(sysSurvey.getId(), RevType.公告.getValue());
            List<SysUser> listUser = sysUserDao.getListByRoleList(list);
            List<SysNotification> sysNotificationList = new ArrayList<>();
            for (SysUser user : listUser) {
                SysNotification sysNotification = new SysNotification();
                sysNotification.setCreaterid(loginInfo.getID());
                sysNotification.setCreater(loginInfo.getFullName());
                sysNotification.setState(State.正常.getValue());
                sysNotification.setCreatetime(date);
                sysNotification.setSourceid(sysSurvey.getId());
                sysNotification.setTargetkey(user.getId().toString());
                sysNotification.setTargettype(TypeEnums.TargetType.个人.getValue());
                sysNotification.setTitle(sysSurvey.getTitle());
                sysNotification.setType(TypeEnums.NotificationType.公告.getValue());
                sysNotificationList.add(sysNotification);
            }
            sysNotificationDao.insertBatch(sysNotificationList);
        }



        return sysSurveyDao.update(sysSurvey);
    }

    //调研编号，自动生成SV20170303001
    public String createSurveyNumber() {
        StringBuilder surveyNumber = new StringBuilder();

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SysSurvey sysSurvey = sysSurveyDao.selectLast();
        surveyNumber.append("SV");
        surveyNumber.append(df.format(date).replace("-", ""));

        if (sysSurvey == null || !df.format(date).equals(df.format(sysSurvey.getCreatetime()))) {
            surveyNumber.append("001");
        } else {
            int num;
            try {
                num = Integer.parseInt(sysSurvey.getSvnumber().substring(sysSurvey.getSvnumber().length() - 3)) + 1;
            } catch (Exception e) {
                num = 1;

            }
            if (num < 10) {
                surveyNumber.append("00" + num);
            } else if (num < 100) {
                surveyNumber.append("0" + num);

            } else {
                surveyNumber.append("0" + num);
            }
        }
        return surveyNumber.toString();
    }

}


