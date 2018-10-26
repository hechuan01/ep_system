package com.zx.system.service;
import com.zx.system.model.SysReceipt;
import org.springframework.stereotype.Service;

@Service
public interface ReceiptsService {

    /*
     * 添加反馈
     * */
    int insert(SysReceipt sysReceipt);
    /*
     * 修改反馈
     * */
    int update(SysReceipt sysReceipt);
}

