package com.zx.system.service;


import com.zx.common.utils.FileReturn;
import com.zx.system.model.SysNotice;
import com.zx.system.model.UserLogin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface NoticesService {

    /*
     * 添加公告，并关联角色和文件
     * */
    int insert(SysNotice sys_notices, List<Integer> roleList, FileReturn fileReturn, UserLogin loginInfo);

    int selectCount(Map map);

    /**
     * 查询分页数据
     *
     * @param map 查询条件
     * @return
     */
    List<SysNotice> getList(Map map);

    /**
     * 根据ID查询 SysNotice
     *
     * @param id
     * @return
     */
    SysNotice selectById(int id);

    /**
     * 根据公告id获取关联角色id
     *
     * @param targetid
     * @param revtype
     * @return
     */
    List<Integer> getRolesByNoticeid(int targetid, int revtype);

    /*
     * 修改公告，并关联角色
     * */
    void update(SysNotice sys_notices, List<Integer> roleList, FileReturn fileReturn, String ROOT_PATE, UserLogin loginInfo);

    /*
     * 删除公告
     * */
    int deleteById(int id);

        /*状态(-1删除0待启动1公告中2已取消3已结束)
         *定时更新状态方法
         */

    void updateNoticeAndsSurveyState();

    /*
     * 发布公告
     * */
    int releaseNoticesByid(int id ,UserLogin loginInfo);
}

