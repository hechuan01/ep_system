package com.zx.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zx.common.controller.BaseController;
import com.zx.common.enums.NoticeOrSurveyState;
import com.zx.common.enums.RevType;
import com.zx.common.utils.FileReturn;
import com.zx.common.utils.FileUtil;
import com.zx.common.utils.PagerModel;
import com.zx.common.utils.ReturnModel;
import com.zx.system.model.SysReceipt;
import com.zx.system.model.SysSurvey;
import com.zx.system.service.ReceiptsService;
import com.zx.system.service.SurveysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * sys_surveys Controller类
 */
@Controller
@RequestMapping("/surveys")
public class SurveysController extends BaseController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private SurveysService surveysService;
    @Autowired
    private ReceiptsService receiptsService;

    /*
    * 添加调研界面
    *
    * */
    @RequestMapping("/add")
    public String Add() {
        return "surveys/add";
    }

    /*
    * 修改调研界面
    *
    * */
    @RequestMapping("/{surveysid}/edit")
    public String edit(@PathVariable("surveysid") int surveysid, Model model) {
        model.addAttribute("surveysid", surveysid);
        return "surveys/edit";
    }

    /*
         * 调研查看回复界面
         *
         * */
    @RequestMapping("/{surveysid}/reply")
    public String reply(@PathVariable("surveysid") int surveysid, String type, Model model) {
        SysSurvey sysSurveys = surveysService.selectById(surveysid);
        model.addAttribute("model", sysSurveys);
        model.addAttribute("type", type);
        return "surveys/reply";
    }

    /*
   * 调研列表页
   * */
    @RequestMapping("/list")
    public String List(String type, Model model) {
        model.addAttribute("type", type);
        return "surveys/list";
    }

    /////////////////////////////////////////方法/////////////////////////////
    /*
    * 提交表单
    *
    * */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel Submit(SysSurvey sysSurvey
            , @RequestParam("file") MultipartFile file, @RequestParam("selectedList") String selectedList) {
        String ROOT_PATE = request.getSession().getServletContext().getRealPath("/");
        FileReturn fileReturn = FileUtil.saveMultipartFile(file, ROOT_PATE, "/upload/survey/");// saveMultipartFile


        List<Integer> selectedListInt = new ArrayList<>();
        for (String u : selectedList.split(",")) {
            selectedListInt.add(Integer.parseInt(u));
        }
        int num = surveysService.insert(sysSurvey, selectedListInt, fileReturn, loginInfo);
        return new ReturnModel(num > 0, num > 0 ? (sysSurvey.getStarttime() != null ? "发布成功" : "保存成功！") : "添加失败！");
    }

    /*
          * 发布调研
          *
          * */
    @RequestMapping(value = "/releaseSurveys", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel releaseSurveys(int id) {

        int num = surveysService.releaseSurveysByid(id, loginInfo);
        return new ReturnModel(num > 0, num > 0 ? "发布成功！" : "发布失败！");
    }

    /*
    * 获取调研列表
    *
    * */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public Object getList(Integer pageSize, Integer pageIndex, String type, String title) throws JsonProcessingException {
        if (pageSize == null) {
            pageSize = 10;
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }

        Map paramMap = new HashMap<String, String>();
        paramMap.put("pageSize", pageSize);
        paramMap.put("start", (pageIndex - 1) * pageSize);
        paramMap.put("orderBy", "createtime desc");
        if (title != null && !title.equals("")) {
            paramMap.put("title", title);
        }
        if (type.equals("user")) {//当为我的调研时
            paramMap.put("roleid", loginInfo.getRole().getId());
            paramMap.put("state", NoticeOrSurveyState.公告中.getValue());
        }
        int Count = surveysService.selectCount(paramMap);
        List<SysSurvey> sysSurveyList = surveysService.getList(paramMap);
        PagerModel result = new PagerModel(pageSize, pageIndex, Count, sysSurveyList);
        return result;
    }

    /*
       * 根据id获取调研信息
       *
       * */
    @RequestMapping(value = "/{surveysid}/getById", method = RequestMethod.POST)
    @ResponseBody
    public SysSurvey getById(@PathVariable("surveysid") int surveysid) {
        SysSurvey sysSurvey = surveysService.selectById(surveysid);
        return sysSurvey;
    }

    /*
    * 获取调研关联角色id
    * */
    @RequestMapping(value = "/{surveysid}/getSurveysRole", method = RequestMethod.POST)
    @ResponseBody
    public List<Integer> getSurveysRole(@PathVariable("surveysid") int surveysid) {
        List<Integer> Roles = surveysService.getRolesByNoticeOrSurveyId(surveysid, RevType.调研.getValue());
        return Roles;

    }

    /*
     * 修改调研
     *
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel update(SysSurvey sysSurvey
            , @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("selectedList") String selectedList) {
        String ROOT_PATE = request.getSession().getServletContext().getRealPath("/");

        FileReturn fileReturn = FileUtil.saveMultipartFile(file, ROOT_PATE, "/upload/survey/");// saveMultipartFile
        List<Integer> selectedListInt = new ArrayList<>();
        for (String u : selectedList.split(",")
                ) {
            selectedListInt.add(Integer.parseInt(u));
        }
        surveysService.update(sysSurvey, selectedListInt, fileReturn, ROOT_PATE, loginInfo);
        return new ReturnModel(true, "修改成功！");
    }

    /*
     * 删除调研
     *
     * */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel deleteById(int id) {
        int num = surveysService.deleteById(id);
        return new ReturnModel(num > 0, num > 0 ? "删除成功！" : "删除失败！");

    }

    /*
    * 反馈调研
    *
    * */
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel feedback(int id, @RequestParam("content") String content) {
        SysSurvey sysSurveys = surveysService.selectById(id);
        SysReceipt sysReceipt;
        int num = 0;

        if (sysSurveys.getSysReceipt() != null) {
            sysReceipt = sysSurveys.getSysReceipt();
            sysReceipt.setContent(content);
            num = receiptsService.update(sysReceipt);
        } else {
            if (sysSurveys.getSysReceipt() == null) {
                sysReceipt = new SysReceipt();
                sysReceipt.setCreaterid(loginInfo.getID());
                sysReceipt.setTargetid(sysSurveys.getId());
                sysReceipt.setCreatetime(new Date());
                sysReceipt.setRectype(RevType.调研.getValue());
                sysReceipt.setContent(content);
                num = receiptsService.insert(sysReceipt);
            }

        }

        return new ReturnModel(num > 0, num > 0 ? "反馈成功！" : "反馈失败！");

    }


}

