package com.zx.system.service;

import com.zx.system.model.SysCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanght on 2017/3/20.
 */

@Service
public interface SysCategoryService {
    /**
     * 根据编码获取其和下级数据
     * @param ctcode
     * @return
     */
    List<SysCategory> selectByctcode(String ctcode);
}
