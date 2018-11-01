package com.zx.system.controller;


import com.zx.common.controller.BaseController;
import com.zx.common.enums.TypeEnums;
import com.zx.common.utils.ReturnModel;
import com.zx.common.utils.TreeJsonEntity;
import com.zx.system.model.SysModule;
import com.zx.system.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门控制器
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;

    @RequestMapping("/list")
    public String list() {
        return "module/list";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(String parentCode, String parentName, Model model) {
        SysModule sysModule = new SysModule();
        sysModule.setParentCode(parentCode);
        sysModule.setParentName(parentName);
        model.addAttribute("sysModule", sysModule);
        return "module/edit";
    }

    /**
     * 编辑菜单页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(String id, Model model) {
        SysModule sysModule = new SysModule();
        if (id != null) {
            sysModule = moduleService.selectById(id);
            if (!sysModule.getParentCode().isEmpty())
                sysModule.setParentName(moduleService.selectByCode(sysModule.getParentCode()).getMname());
        }
        model.addAttribute("sysModule", sysModule);
        return "module/edit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Object submit(SysModule sysModule, String parentCode, Model model) {
        ReturnModel rm = new ReturnModel();
        try {
            if (sysModule.getId() == null || "".equals(sysModule.getId())) {
                Map.Entry entry = generateModuleSubCode(parentCode);
                if (entry != null && (boolean) entry.getKey()) {
                    sysModule.setMcode(entry.getValue().toString());
                    sysModule.setState(0);
                } else {
                    rm.setState(false);
                    rm.setMessage(entry.getValue().toString());
                    return rm;
                }
            }
            moduleService.update(sysModule);
            rm.setState(true);
            rm.setMessage("修改成功");
        } catch (Exception ex) {
            rm.setState(false);
            rm.setMessage("系统异常，稍后再试");
        }
        return rm;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        ReturnModel msg = new ReturnModel();
        if (id != null) {
            try {
                SysModule sysModule = moduleService.selectById(id);
                sysModule.setState(-1);
                moduleService.update(sysModule);
                msg.setState(true);
                msg.setMessage(sysModule.getMname() + "删除成功");
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
     * 获取部门树形展示的Json数据
     */
    @RequestMapping("treeJson")
    @ResponseBody
    public Object treeJson(String code) {
        String parentCode = code != null ? code : "";
        List<SysModule> list = moduleService.getList();
        List<TreeJsonEntity> treeList = depts2TreeJsonEntity(list.stream().filter(l -> l.getParentCode().equals(parentCode))
                .collect(Collectors.toList()), list);
        return treeList;
    }

    /**
     * 部门对象转成树形对象
     *
     * @param nodes
     * @param all
     * @return
     */
    public List<TreeJsonEntity> depts2TreeJsonEntity(List<SysModule> nodes, List<SysModule> all) {
        List<TreeJsonEntity> returnList = new ArrayList<>();
        for (SysModule item : nodes) {
            TreeJsonEntity entity = new TreeJsonEntity();
            entity.id = item.getId();
            entity.code = item.getMcode();
            entity.text = item.getMname();
            entity.value = item.getId().toString();
            entity.complete = true;
            entity.hasChildren = all.stream().filter(l -> l.getParentCode().equals(item.getMcode())).count() > 0;
            entity.childNodes = depts2TreeJsonEntity(all.stream().filter(l -> l.getParentCode().equals(item.getMcode()))
                    .collect(Collectors.toList()), all);
            entity.img = (item.getMicon() == null || item.getMicon().isEmpty()) ? "angle-right" : item.getMicon().trim();
            entity.extra = new ExtraObject(item.getMurl(), item.getState(), item.getSortnum(), item.getRemark(), TypeEnums.ModuleType.getName(item.getMtype()));
            returnList.add(entity);
        }
        return returnList;
    }

    class ExtraObject {
        public ExtraObject(String murl, Integer state, Integer sortNum, String remark, String moduleType) {
            this.murl = murl;
            this.state = state;
            this.remark = remark;
            this.moduleType = moduleType;
            this.sortNum = sortNum;
        }

        public String murl;
        public Integer state;
        public String remark;
        public String moduleType;
        public Integer sortNum;
    }
}
