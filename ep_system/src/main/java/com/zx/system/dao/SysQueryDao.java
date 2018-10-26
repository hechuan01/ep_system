package com.zx.system.dao;
import com.zx.system.model.SysQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 描述：${SysQuery数据操作接口}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-20 16:52
 **/
@Repository(value = "SysQueryDao")
public interface SysQueryDao {
    //插入数据
    int insert(SysQuery sysQuery);
    //根据主键获取对象
    SysQuery selectById(Integer id);
    //查询符合条件的数据条数
    int selectCount(Map map);
    //查询符合条件的数据集合
    List<SysQuery> selectList(Map map);
    //更新
    int update(SysQuery sysQuery);

    //根据id删除
    int deleteById(Integer id);
}
