package com.zx.xt.service;

import com.zx.xt.model.CmtInfo;

import java.util.List;

/**
 * Created by wanght on 2017/3/15.
 */
public interface CmtInfoService {
    /**
     * 插入记录
     */
    int insert(CmtInfo cmtInfo);

    /**
     * 根据Id删除
     */
    int deleteById(Integer id);

    /**
     * 根据Id获取对象
     */
    CmtInfo selectById(Integer id);

    /**
     * 根据Id查询评论列表
     */
    List<CmtInfo> selectList(Integer targetid,Integer targettype);
}
