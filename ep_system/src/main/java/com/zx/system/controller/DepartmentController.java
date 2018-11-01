package com.zx.system.controller;

import com.zx.common.controller.BaseController;
import com.zx.common.utils.ReturnModel;
import com.zx.common.utils.TreeJsonEntity;
import com.zx.system.model.SysDepartment;
import com.zx.system.service.DepartmentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 部门控制器
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    @Resource
    private DepartmentService departmentService;

    @RequestMapping("list")
    public String list() {
        return "department/list";
    }


    /**
     * 编辑部门页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(String id, String parentCode, Model model) {
        SysDepartment sysDepartment = new SysDepartment();
        if (id != null) {
            sysDepartment = departmentService.selectById(id);
        }
        model.addAttribute("sysDepartment", sysDepartment);
        model.addAttribute("parentCode", parentCode);
        SysDepartment pDept = departmentService.selectByCode(parentCode);
        model.addAttribute("parentName", pDept != null ? pDept.getDeptname() : "");
        return "department/edit";
    }

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    public Object submit(SysDepartment sysDepartment, String parentCode, Model model) {
        ReturnModel rm = new ReturnModel();
        try {

            if (sysDepartment.getId() == null || "".equals(sysDepartment.getId())) {
                Map.Entry entry = generateDeptSubCode(parentCode);
                if (entry != null && (boolean) entry.getKey()) {
                    sysDepartment.setDeptcode(entry.getValue().toString());
                    sysDepartment.setState(0);
                    sysDepartment.setCreaterid(1);
                } else {
                    rm.setState(false);
                    rm.setMessage(entry.getValue().toString());
                    return rm;
                }
            }
            departmentService.update(sysDepartment);
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
                SysDepartment sysDepartment = departmentService.selectById(id);
                sysDepartment.setState(-1);
                departmentService.update(sysDepartment);
                msg.setState(true);
                msg.setMessage(sysDepartment.getDeptname() + "删除成功");
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
    public Object treeJson() {
        String code = loginInfo.getDeptCode();
        List<SysDepartment> list = getDepartmentList(code);
        List<TreeJsonEntity> treeList = depts2TreeJsonEntity(list.stream().filter(__ -> __.getDeptcode().equals(code))
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
    public List<TreeJsonEntity> depts2TreeJsonEntity(List<SysDepartment> nodes, List<SysDepartment> all) {
        List<TreeJsonEntity> returnList = new ArrayList<>();
        for (SysDepartment item : nodes) {
            TreeJsonEntity entity = new TreeJsonEntity();
            entity.id = item.getId();
            entity.code = item.getDeptcode();
            entity.text = item.getDeptname();
            entity.value = item.getId().toString();
            entity.complete = true;
            entity.hasChildren = departmentService.getSubDepartments(all, item.getDeptcode()).size() > 0;
            entity.childNodes = depts2TreeJsonEntity(departmentService.getSubDepartments(all, item.getDeptcode()), all);
            entity.img = "leaf";
            entity.extra = new ExtraObject(item.getCreatetime(), item.getState(), item.getRemark());
            returnList.add(entity);
        }
        return returnList;
    }

    class ExtraObject {
        public ExtraObject(java.util.Date date, Integer state, String remark) {
            this.createtime = date;
            this.state = state;
            this.remark = remark;
        }

        public Date createtime;
        public Integer state;
        public String remark;
    }
}

