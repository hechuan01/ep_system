package com.zx.system.dao;

import com.zx.system.model.SysReceipt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sysReceiptDao")
public interface SysReceiptDao {

    /**
     * 插入记录
     *
     * @param sysReceipt
     *
     * @return
     */
    int insert(SysReceipt sysReceipt);

    int update(SysReceipt sysReceipt);
}


