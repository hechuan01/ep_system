package com.zx.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zx.common.controller.BaseController;
import com.zx.common.utils.EncryptUtil;
import com.zx.common.utils.PagerModel;
import com.zx.common.utils.ReturnModel;
import com.zx.system.model.SysDepartment;
import com.zx.system.model.SysRole;
import com.zx.system.model.SysUser;
import com.zx.system.service.DepartmentService;
import com.zx.system.service.RoleService;
import com.zx.system.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by V.E. on 2017/3/9.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger logger = LogManager.getLogger(UserController.class);
    @Resource
    private UserService userService;

    /**
     * role service
     */
    @Resource
    private RoleService roleService;
    @Resource
    private DepartmentService departmentService;

    @RequestMapping(value = "/edit")
    public String edit(Model model, String id) {
        SysUser user = new SysUser();
        if (id != null) {
            user = userService.selectById(id);
            model.addAttribute("isNew", false);
        } else {
            model.addAttribute("isNew", true);
        }
        user.setPsd(null);
        model.addAttribute("sysUser", user);
        Map paramMap = new HashMap();
        List<SysRole> roles = roleService.getList(paramMap);
        List<SysDepartment> depts = getDepartmentList(user.getDeptcode());
        model.addAttribute("roles", roles);
        model.addAttribute("departments", depts);
        return "user/edit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Object submit(SysUser sysUser, Model model, String id) throws JsonProcessingException {
        ReturnModel rm = new ReturnModel();
        try {
            if (sysUser != null && sysUser.getPsd() != null && sysUser.getPsd().trim() != "")
                sysUser.setPsd(EncryptUtil.getMD5Encryption(sysUser.getPsd()));
            else
                sysUser.setPsd(null);
            sysUser = userService.update(sysUser);
            rm.setState(true);
            rm.setMessage("添加成功");
        } catch (Exception e) {
            rm.setState(false);
            rm.setMessage("添加失败");
        }
        return rm;
    }

    @RequestMapping(value = "/list")
    public String list() {
        return "user/list";
    }

    // 返回值类型为json
    @RequestMapping(value = "/getList")
    @ResponseBody
    public Object getList(Integer pageSize, Integer pageIndex, String fullName) throws JsonProcessingException {
        if (pageSize == null) {
            pageSize = 15;
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }
        int userCount = userService.selectCount();
        Map paramMap = new HashMap<String, String>();
        paramMap.put("state", 0);
        //paramMap.put("pageSize", pageSize);
        paramMap.put("start", (pageIndex - 1) * pageSize);
        paramMap.put("end", pageIndex * pageSize);
        paramMap.put("orderBy", "id asc");
        paramMap.put("fullnameFuzzy", fullName);
        List<SysUser> userList = userService.getList(paramMap);
        PagerModel result = new PagerModel(pageSize, pageIndex, userCount, userList);
        return result;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        ReturnModel msg = new ReturnModel();
        if (id != null) {
            try {
                SysUser user = userService.selectById(id);
                user.setState(-1);
                userService.update(user);
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

    @RequestMapping(value = "/detail")
    public String detail(Model model, Integer id) {
        model.addAttribute("user", loginInfo);
        return "user/detail";
    }

    @RequestMapping(value = "/password")
    public String password(Model model, Integer id) {
        model.addAttribute("id", id);
        return "user/password";
    }

    @ResponseBody
    @RequestMapping(value = "/changePassword")
    public Object changePassword(String id, String oldPwd, String newPwd) {
        SysUser sysUser = userService.selectById(id);
        ReturnModel msg = new ReturnModel();
        try {
            if (sysUser.getPsd().equals(EncryptUtil.getMD5Encryption(oldPwd))) {
                sysUser.setPsd(EncryptUtil.getMD5Encryption(newPwd));
                userService.update(sysUser);
                msg.setState(true);
                msg.setMessage("密码修改成功，请重新登录！");
            } else {
                msg.setState(false);
                msg.setMessage("原密码错误，请重新输入！");
            }
        } catch (Exception ex) {
            msg.setState(false);
            msg.setMessage("系统异常，请稍后再试！");
        }
        return msg;
    }

}
