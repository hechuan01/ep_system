package com.zx.common.controller;


import com.zx.common.utils.ReturnModel;
import com.zx.system.model.SysDepartment;
import com.zx.system.model.SysModule;
import com.zx.system.model.SysNotification;
import com.zx.system.model.UserLogin;
import com.zx.system.service.DepartmentService;
import com.zx.system.service.ModuleService;
import com.zx.system.service.NotificationService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by V.E. on 2017/3/9.
 */

public class BaseController {

    @Resource
    private NotificationService notificationService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private ModuleService moduleService;
    public static final String SUCCESS = "common/success";
    public static List<String> allModules = new ArrayList<String>();

    @PostConstruct
    public void init() {
        if (allModules == null || allModules.size() <= 0) {
            List<SysModule> modules = moduleService.getList();
            List<String> all = new ArrayList<>();
            all = all.stream().distinct().collect(Collectors.toList());
            if (modules.size() > 0) {
                List<String> finalAllModules = all;
                modules.forEach(l -> {
                            if (!l.getMurl().isEmpty()) {
                                finalAllModules.add(l.getMurl());
                            }
                        }
                );
                allModules = finalAllModules;
            }
        }
    }

    /**
     * 全局变量：登录后的信息
     */
    public static UserLogin loginInfo = null;


    public void isLogin(HttpSession httpSession) {
        this.loginInfo = (UserLogin) httpSession.getAttribute("userLogin");
    }


    /**
     * 根据当前部门code生成下级部门code
     *
     * @param code
     * @return
     */
    public Map.Entry<Boolean, String> generateDeptSubCode(String code) {
        if (code != null && code.trim() != "") {
            HashMap map = new HashMap();
            List<SysDepartment> subList = departmentService.getSubDepartments(code);
            Integer currentMax = 0;
            List<Integer> subCodeList = new ArrayList<>();
            if (subList.size() > 0) {
                for (SysDepartment sysDepartment : subList) {
                    subCodeList.add(Integer.parseInt(sysDepartment.getDeptcode().substring(sysDepartment.getDeptcode().length() - 3, sysDepartment.getDeptcode().length())));
                }
                Collections.sort(subCodeList, Comparator.reverseOrder());
                currentMax = subCodeList.get(0);
            }
            if (currentMax >= 999) {
                map.put(false, "部门数量已到上限，不可以继续创建");
            } else {
                map.put(true, code + String.format("%03d", currentMax + 1));
            }
            java.util.Iterator it = map.entrySet().iterator();
            if (it.hasNext()) {
                return (java.util.Map.Entry) it.next();
            }
        }
        return null;
    }

    public Map.Entry generateModuleSubCode(String parentCode) {
        HashMap map = new HashMap();
        List<SysModule> subList = moduleService.getSubModules(parentCode);
        Integer currentMax = 0;
        List<Integer> subCodeList = new ArrayList<>();
        if (subList.size() > 0) {
            for (SysModule sysModule : subList) {
                subCodeList.add(Integer.parseInt(sysModule.getMcode().substring(sysModule.getMcode().length() - 3, sysModule.getMcode().length())));
            }
            Collections.sort(subCodeList, Comparator.reverseOrder());
            currentMax = subCodeList.get(0);
        }
        if (currentMax >= 999) {
            map.put(false, "菜单数量已到上限，不可以继续创建");
        } else {
            map.put(true, parentCode + String.format("%03d", currentMax + 1));
        }
        java.util.Iterator it = map.entrySet().iterator();
        if (it.hasNext()) {
            return (java.util.Map.Entry) it.next();
        }
        return null;
    }


    /**
     * 查询当前code所有子节点
     *
     * @param code
     * @return
     */
    public List<SysDepartment> getDepartmentList(String code) {
        List<SysDepartment> list = departmentService.getSubsetsBranchByCode(code);
        return list;
    }

    /**
     * 查询所有菜单
     */
    public List<SysModule> getModuleList() {
        List<SysModule> list = moduleService.getList();
        return list;
    }

    public Object Notify(SysNotification model){
        ReturnModel rm =new ReturnModel();
        notificationService.update(model);
        return rm;
    }


}
