package com.zx.system.controller;

import com.zx.common.controller.BaseController;
import com.zx.common.enums.TypeEnums;
import com.zx.common.utils.NotificationModel;
import com.zx.common.utils.PagerModel;
import com.zx.common.utils.ReturnModel;
import com.zx.system.model.SysNotification;
import com.zx.system.model.SysUser;
import com.zx.system.service.NotificationService;
import com.zx.system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class description
 *
 * @author V.E.
 * @version 1.0, 17/03/25
 */
@Controller
@RequestMapping("/notification")
public class NotificationController extends BaseController {
    @Resource
    private NotificationService notificationService;
    @Resource
    private UserService userService;


    @RequestMapping(value = "/get")
    @ResponseBody
    public Object get() {
        SysUser sysUser = userService.selectById(loginInfo.getID());
        Map map = new HashMap();
        map.put("state", 0);
        map.put("userId", sysUser.getId());
        map.put("deptId", sysUser.getDeptcode());
        map.put("roleId", sysUser.getSysRole().getId());
        List<SysNotification> noticeList = notificationService.getUnHandleNotifications(TypeEnums.NotificationType.公告, map);
        List<SysNotification> surveyList = notificationService.getUnHandleNotifications(TypeEnums.NotificationType.调研, map);
        List<SysNotification> flowList = notificationService.getUnHandleNotifications(TypeEnums.NotificationType.流程, map);
        NotificationModel model = new NotificationModel();
        model.setAllCount(noticeList.size() + surveyList.size() + flowList.size());
        model.setNoticesCount(noticeList.size());
        model.setSurveysCount(surveyList.size());
        model.setAppliesCount(flowList.size());
        return model;
    }

    @RequestMapping(value = "/list")
    public String list(String type, Model model) {
        model.addAttribute("type", type);
        return "notification/list";
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    public Object getList(Integer pageSize, Integer pageIndex, Integer state, Integer type) {
        if (pageSize == null) {
            pageSize = 15;
        }

        if (pageIndex == null) {
            pageIndex = 1;
        }
        SysUser sysUser = userService.selectById(loginInfo.getID());
        NotificationModel model = new NotificationModel();
        Map map = new HashMap();
        map.put("state", state);
        map.put("type", type);
        map.put("userId", sysUser.getId());
        map.put("deptId", sysUser.getDeptcode());
        map.put("roleId", sysUser.getSysRole().getId());
        map.put("pageSize", pageSize);
        map.put("start", (pageIndex - 1) * pageSize);
        map.put("orderBy", "createtime desc");
        int count = notificationService.selectCount(map);
        List<SysNotification> list = notificationService.selectList(map);
        PagerModel result = new PagerModel(pageSize, pageIndex, count, list);

        return result;
    }

    @RequestMapping(value = "/deleteNotify")
    @ResponseBody
    public Object deleteNotify(String ids) {
        ReturnModel rm = new ReturnModel();
        try {
            notificationService.deleteList(ids);
            rm.setState(true);
            rm.setMessage("删除成功");
        } catch (Exception e) {
            rm.setState(false);
            rm.setMessage("删除失败");
        }
        return rm;
    }

    @RequestMapping(value = "/deleteBySID")
    @ResponseBody
    public Object deleteBySID(Integer sid, Integer type) {
        ReturnModel rm = new ReturnModel();
        try {
            notificationService.deleteBySID(sid, type);
            rm.setState(true);
            rm.setMessage("删除成功");
        } catch (Exception e) {
            rm.setState(false);
            rm.setMessage("删除失败");
        }
        return rm;
    }


}



