package com.zx.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zx.common.controller.BaseController;
import com.zx.common.enums.RoleType;
import com.zx.common.utils.PagerModel;
import com.zx.common.utils.ReturnModel;
import com.zx.system.model.SysModule;
import com.zx.system.model.SysRole;
import com.zx.system.model.SysRolemodule;
import com.zx.system.service.ModuleService;
import com.zx.system.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    /**
     * role service
     */
    @Resource
    private RoleService roleService;
    @Resource
    private ModuleService moduleService;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(Integer id) {
        ReturnModel msg = new ReturnModel();

        if (id != null) {
            try {
                SysRole role = roleService.selectById(id.intValue());

                role.setState(-1);
                roleService.update(role);
                msg.setState(true);
                msg.setMessage("删除成功");
            } catch (Exception ex) {
                msg.setState(false);
                msg.setMessage("系统异常，稍后再试");
            }
        } else {
            msg.setState(false);
            msg.setMessage("没有找到删除的对象");
        }

        return msg;
    }

    /**
     * Method description
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit.html")
    public String edit(Model model, Integer id) {
        SysRole role = new SysRole();

        if (id != null) {
            role = roleService.selectById(id.intValue());
        }

        model.addAttribute("sysRole", role);
        model.addAttribute("roleType", RoleType.toList());

        return "role/edit";
    }

    /**
     * Method description
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "role/list";
    }

    /**
     * Method description
     *
     * @param role
     * @param model
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Object submit(SysRole role, Model model, Integer id) throws JsonProcessingException {
        ReturnModel rm = new ReturnModel();
        try {
            role = roleService.update(role);
            rm.setState(true);
            rm.setMessage("添加成功");
        } catch (Exception e) {
            rm.setState(false);
            rm.setMessage("添加失败");
        }
        return rm;
    }

    // 返回值类型为json

    /**
     * Method description
     *
     * @param pageSize
     * @param pageIndex
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public Object getList(Integer pageSize, Integer pageIndex,String roleName) throws JsonProcessingException {
        if (pageSize == null) {
            pageSize = 5;
        }

        if (pageIndex == null) {
            pageIndex = 1;
        }

        int userCount = roleService.selectCount();
        Map paramMap = new HashMap<String, String>();
        paramMap.put("state", 0);
        //paramMap.put("pageSize", pageSize);
        paramMap.put("start", (pageIndex - 1) * pageSize);
        paramMap.put("end", pageIndex * pageSize);
        paramMap.put("orderBy", "createtime desc");
        paramMap.put("rolenameFuzzy", roleName);

        List<SysRole> userList = roleService.selectList(paramMap);
        PagerModel result = new PagerModel(pageSize, pageIndex, userCount, userList);

        return result;
    }

    @RequestMapping("/module")
    @SuppressWarnings("unchecked")
    public String module(Model model, Integer id) {
        model.addAttribute("id", id);
        //全部模块
//        List<SysModule> sysModules = moduleService.getList();
//        model.addAttribute("sysModules", sysModules);
//        request.setAttribute("sysModules", sysModules);
        //获取角色已分配的模块
        SysRole sysRole = roleService.selectById(id);
        List<Integer> ids = new ArrayList<>();
        for (SysModule item : sysRole.getModules()) {
            ids.add(item.getId());
        }
        model.addAttribute("mids", StringUtils.join(ids, ","));
        return "role/module";
    }

    @RequestMapping(value = "/submitModule")
    @ResponseBody
    public Object submitModule(Integer id, String checkedIDs) throws JsonProcessingException {
//        role = roleService.update(role);
        ReturnModel rm = new ReturnModel();
        //插入角色菜单关联表
        try {
            List<Integer> ids = new ArrayList<>();
            for (String item : checkedIDs.split(",")) {
                ids.add(Integer.parseInt(item));
            }

            List<SysModule> list = moduleService.selectByIds(ids);
            SysRole sysRole = roleService.selectById(id);
            List<SysRolemodule> rmList = new ArrayList<>();
            for (SysModule item : list) {
                SysRolemodule model = new SysRolemodule();
                model.setRoleid(sysRole.getId());
                model.setMcode(item.getMcode());
                rmList.add(model);

            }
            roleService.updateRoleModules(sysRole.getId(),rmList);
            rm.setState(true);
            rm.setMessage("角色权限设置成功");
        } catch (Exception e) {
            rm.setState(false);
            rm.setMessage("角色权限设置失败");
        }
        return rm;
    }

    /**
     * Method description
     *
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/getAllRole", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllRole() throws JsonProcessingException {
        int userCount = roleService.selectCount();
        Map paramMap = new HashMap<String, String>();
        paramMap.put("state", 0);
        paramMap.put("orderBy", "createtime desc");
        List<SysRole> userList = roleService.getList(paramMap);
        return userList;
    }
}


