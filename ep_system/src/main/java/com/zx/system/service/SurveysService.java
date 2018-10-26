package com.zx.system.service;


import com.zx.common.utils.FileReturn;
import com.zx.system.model.SysSurvey;
import com.zx.system.model.UserLogin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface SurveysService {

    /*
     * 添加调研，并关联角色
     * */
    int insert(SysSurvey sysSurvey, List<Integer> roleList, FileReturn fileReturn, UserLogin loginInfo);

    int selectCount(Map map);

    /**
     * 查询分页数据
     *
     * @param map 查询条件
     * @return
     */
    List<SysSurvey> getList(Map map);

    /**
     * 根据ID查询 SysNotice
     *
     * @param id
     * @return
     */
    SysSurvey selectById(int id);

    /**
     * 根据调研id获取关联角色id
     *
     * @param targetid
     * @param revtype
     * @return
     */
    List<Integer> getRolesByNoticeOrSurveyId(int targetid, int revtype);

    /*
     * 修改调研，并关联角色
     * */
    void update(SysSurvey sysSurvey, List<Integer> roleList,FileReturn fileReturn,String ROOT_PATE, UserLogin loginInfo);

    /*
     * 删除调研
     * */
    int deleteById(int id);
    /*
     * 发布调研
     * */
    int releaseSurveysByid(int id,UserLogin loginInfo);
}

