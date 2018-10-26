package com.zx.system.service;
import com.zx.system.model.SysQuery;
import java.util.*;
/**
 * 描述：${SysQuery服务接口}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-20 18:50
 **/
public interface SysQueryService {
    //根据ID查询
    SysQuery selectById(int id);
    //根据指定的条件查询记录条数
    int selectCount(Map map);
    //插叙数据
//    SysQuery insert(SysQuery sysQuery);
    //更新数据
    SysQuery update(SysQuery sysQuery);
    //根据指定的条件查询集合
    List<SysQuery> selectList(Map map);

    //根据id删除
    boolean deleteById(int id);

}

