package com.zx.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zx.common.controller.BaseController;
import com.zx.common.enums.NoticeOrSurveyState;
import com.zx.common.enums.RevType;
import com.zx.common.utils.FileReturn;
import com.zx.common.utils.FileUtil;
import com.zx.common.utils.PagerModel;
import com.zx.common.utils.ReturnModel;
import com.zx.system.model.SysAttachment;
import com.zx.system.model.SysNotice;
import com.zx.system.model.SysReceipt;
import com.zx.system.service.AttachmentsService;
import com.zx.system.service.NoticesService;
import com.zx.system.service.ReceiptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * sys_notices Controller类
 */
@Controller
@RequestMapping("/notices")
public class NoticesController extends BaseController {
    static ScheduledExecutorService service;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private NoticesService noticesService;
    @Autowired
    private AttachmentsService attachmentsService;
    @Autowired
    private ReceiptsService receiptsService;

    /*
    * 添加公告界面
    *
    * */
    @RequestMapping("/add")
    public String Add() {
        return "notices/add";
    }

    /*
    * 修改公告界面
    *
    * */
    @RequestMapping("/{noticeid}/edit")
    public String edit(@PathVariable("noticeid") int noticeid, Model model) {
        model.addAttribute("noticeid", noticeid);
        return "notices/edit";
    }

    /*
       * 公告查看回复界面
       *
       * */
    @RequestMapping("/{noticeid}/reply")
    public String reply(@PathVariable("noticeid") int noticeid, String type, Model model) {
        SysNotice sysNotice = noticesService.selectById(noticeid);
        model.addAttribute("model", sysNotice);
        model.addAttribute("type", type);
        if (type.equals("user")) { ///用户查看时
            if (sysNotice.getSysReceipt() == null) {
                SysReceipt sysReceipt = new SysReceipt();
                sysReceipt.setCreaterid(loginInfo.getID());
                sysReceipt.setTargetid(sysNotice.getId());
                sysReceipt.setCreatetime(new Date());
                sysReceipt.setRectype(RevType.公告.getValue());
                sysReceipt.setContent("");
                receiptsService.insert(sysReceipt);
            }

        }
        return "notices/reply";
    }

    /*
       * 公告列表页
       * */
    @RequestMapping("/list")
    public String List(String type, Model model) {
        model.addAttribute("type", type);
        return "notices/list";
    }

    /////////////////////////////////////////方法/////////////////////////////
    /*
    * 提交表单
    *
    * */
    @ResponseBody
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public ReturnModel Submit(SysNotice sysNotice
            , @RequestParam("file") MultipartFile file, @RequestParam("selectedList") String selectedList) {

        String ROOT_PATE = request.getSession().getServletContext().getRealPath("/");
        FileReturn fileReturn = FileUtil.saveMultipartFile(file, ROOT_PATE, "/upload/notice/");// saveMultipartFile


        List<Integer> selectedListInt = new ArrayList<>();
        for (String u : selectedList.split(",")) {
            selectedListInt.add(Integer.parseInt(u));
        }
        ///插入公告
        int num = noticesService.insert(sysNotice, selectedListInt, fileReturn, loginInfo);
        return new ReturnModel(num > 0, num > 0 ? (sysNotice.getStarttime() != null ? "发布成功" : "保存成功！") : "添加失败！");
    }

    /*
    * 获取公告列表
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
        //paramMap.put("state", 0);

        paramMap.put("pageSize", pageSize);
        paramMap.put("start", (pageIndex - 1) * pageSize);
        paramMap.put("orderBy", "createtime desc");
        if (title != null && !title.equals("")) {
            paramMap.put("title", title);
        }
        if (type.equals("user")) {//当为我的公告时
            paramMap.put("roleid", loginInfo.getRole().getId());
            paramMap.put("state", NoticeOrSurveyState.公告中.getValue());//用户只能查看公告中的公告
        }
        int Count = noticesService.selectCount(paramMap);
        List<SysNotice> sysNoticeList = noticesService.getList(paramMap);
        PagerModel result = new PagerModel(pageSize, pageIndex, Count, sysNoticeList);
        return result;
    }

    /*
       * 根据id获取公告信息
       *
       * */
    @RequestMapping(value = "/{noticeid}/getById", method = RequestMethod.POST)
    @ResponseBody
    public SysNotice getById(@PathVariable("noticeid") int noticeid) {
        SysNotice sysNotice = noticesService.selectById(noticeid);
        return sysNotice;
    }

    /*
    * 获取公告关联角色id
    * */
    @RequestMapping(value = "/{noticeid}/getNoticeRole", method = RequestMethod.POST)
    @ResponseBody
    public List<Integer> getNoticeRole(@PathVariable("noticeid") int noticeid) {
        List<Integer> Roles = noticesService.getRolesByNoticeid(noticeid, RevType.公告.getValue());
        return Roles;

    }

    /*
     * 修改公告
     *
     * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel update(SysNotice sysNotice
            , @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("selectedList") String selectedList) {
        String ROOT_PATE = request.getSession().getServletContext().getRealPath("/");

        FileReturn fileReturn = FileUtil.saveMultipartFile(file, ROOT_PATE, "/upload/notice/");// saveMultipartFile
        List<Integer> selectedListInt = new ArrayList<>();
        for (String u : selectedList.split(",")) {
            selectedListInt.add(Integer.parseInt(u));
        }
        noticesService.update(sysNotice, selectedListInt, fileReturn, ROOT_PATE, loginInfo);
        return new ReturnModel(true, "修改成功！");
    }

    /*
     * 删除公告
     *
     * */
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel deleteById(int id) {
        int num = noticesService.deleteById(id);
        return new ReturnModel(num > 0, num > 0 ? "删除成功！" : "删除失败！");
    }

    /*
        * 发布公告
        *
        * */
    @RequestMapping(value = "/releaseNotices", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel releaseNotices(int id) {

        int num = noticesService.releaseNoticesByid(id,loginInfo);
        return new ReturnModel(num > 0, num > 0 ? "发布成功！" : "发布失败！");
    }

    /*
    * 下载公告附件
    * */
    @RequestMapping("/downloadFile")
    public void downloadFile(Integer attachmentid, HttpServletResponse response) {
        /*String ROOT_PATE = request.getSession().getServletContext().getRealPath("/");
        SysAttachment attachment = attachmentsService.selectById(attachmentid);

        return FileUtil.fileDownload(ROOT_PATE, attachment.getAttpath(), attachment.getAttname());*/
        String ROOT_PATE = request.getSession().getServletContext().getRealPath("/");
        SysAttachment attachment = attachmentsService.selectById(attachmentid);

        FileUtil.fileDownload(ROOT_PATE, attachment.getAttpath(), attachment.getAttname(), response);
    }


    ///定时方法
    @RequestMapping(value = "/UpdateNoticeAndsSurveyState", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel updateNoticeAndsSurveyState() {
        Runnable runnable = new Runnable() {
            Calendar updaatetime;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            public void run() {
                try {///使用try/catch 防止程序出现异常线程停止
                    Calendar date = Calendar.getInstance();//当前时间
                    Calendar d1 = Calendar.getInstance();
                    Calendar d2 = Calendar.getInstance();
                    d1.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE), 0, 0, 0);
                    d1.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE), 0, 15, 0);


                    String time1 = df.format(date.getTime());
                    System.out.println("执行" + time1);
                    if (updaatetime == null || (updaatetime.get(Calendar.DATE) != date.get(Calendar.DATE) && date.getTimeInMillis() > d1.getTimeInMillis() && date.getTimeInMillis() < d2.getTimeInMillis())) {//当不知第一次调用时
                        updaatetime = Calendar.getInstance();
                    /*状态(-1删除0待启动1公告中2已取消3已结束)*/
                        String time = df.format(updaatetime.getTime());
                        System.out.println("更新时间" + time);
                        noticesService.updateNoticeAndsSurveyState();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }


            }
        };
        if (service != null) {
            if (!service.isShutdown())
                service.shutdown();
        }
        service = Executors.newSingleThreadScheduledExecutor();


        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 0, 10 * 60, TimeUnit.SECONDS);//点击时立即执行，然后每十分钟执行一次
        return new ReturnModel(true, "成功");
    }

}

