package com.zx.system.dao;

import com.zx.system.model.SysReceive;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysReceiveDao")
public interface SysReceiveDao {

    /**
     * 插入记录
     *
     * @param sysReceive
     *
     * @return
     */
    int insert(SysReceive sysReceive);

    /**
     * 插入多条记录
     *
     * @param sysReceiveList
     *
     * @return
     */
    int insertList(List<SysReceive> sysReceiveList);

    /**
     * 根据公告调研id获取关联角色id
     *
     * @param targetid 公告调研表id
     * @param revtype 公告或者调研
     * @return
     */
    List<Integer> getRolesByNoticeOrSurveyId(int targetid, int revtype);

    /*
    * 根据公告或调研id 删除关联记录
    * */
    void delete(int targetid,int revtype);
}


