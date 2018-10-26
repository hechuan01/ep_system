package com.zx.system.service.impl;

import com.zx.system.dao.SysReceiptDao;
import com.zx.system.model.SysReceipt;
import com.zx.system.service.ReceiptsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service("receiptsService")
@Transactional
public class ReceiptsServiceImpl implements ReceiptsService {

    @Resource
    private SysReceiptDao sysReceiptDao;///附件表

    /*
     * 添加反馈
     * */
    @Override
    public int insert(SysReceipt sysReceipt) {
       return sysReceiptDao.insert(sysReceipt);
    }
    /*
     * 修改反馈
     * */
    @Override
    public int update(SysReceipt sysReceipt) {
        return sysReceiptDao.update(sysReceipt);
    }
}

