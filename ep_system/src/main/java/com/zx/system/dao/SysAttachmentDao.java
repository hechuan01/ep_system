package com.zx.system.dao;

import com.zx.system.model.SysAttachment;
import org.springframework.stereotype.Repository;

import java.util.List;

///系统附件表
@Repository("sysAttachmentDao")
public interface SysAttachmentDao {
    /*
    * 根据id查询
    * */
    SysAttachment selectById(int id);

    /**
     * 插入记录
     *
     * @param sysAttachment
     * @return
     */
    int insert(SysAttachment sysAttachment);

    /**
     * 根据附件类型附件对象主键获取附件
     *
     * @param
     * @return
     */
    List<SysAttachment> getByTargetidAttType(Integer targetid, Integer attType);
    /**
     * 根据Id更新
     */
    int update(SysAttachment sysAttachment);

    int delByTargetidAttType(Integer targetid, Integer attType);;
}


