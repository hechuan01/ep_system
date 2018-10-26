package com.zx.system.controller;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zx.common.controller.BaseController;
import com.zx.common.utils.PagerModel;
import com.zx.common.utils.ReturnModel;
import com.zx.system.model.SysQuery;
import com.zx.system.service.SysQueryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.resources.Messages_pt_BR;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：${SysQuery Controller}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-20 19:20
 **/
@Controller
@RequestMapping(value = "/ip")
public class SysQueryController extends BaseController {
    private static Logger log = LogManager.getLogger(SysQueryController.class);
    @Resource(name = "sysQueryService")
    private SysQueryService sysQueryService;

    /**
     * 加载列表页
     *
     * @return
     */
    @RequestMapping(value = "/sysquerylist")
    public String list(Model model) {
        return "ip/sysquerylist";
    }

    @RequestMapping(value = "/sysquerygetlist")
    @ResponseBody
    public Object getLists(String keywords, Integer pageSize, Integer pageIndex, Model model) {
        try {
            if (pageSize == null) {
                pageSize = 3;
            }
            if (pageIndex == null) {
                pageIndex = 1;
            }
            SysQuery sysQuery = new SysQuery();
            sysQuery.setCreateid(loginInfo.getID());
            sysQuery.setKeywords(keywords);
            sysQuery.setState(-1);
//            Map map = new HashMap<String, String>();
//            map.put("keywordsFuzzy", sysQuery.getKeywords());
//            map.put("createidFuzzy", sysQuery.getCreateid());
//            map.put("stateFuzzy",sysQuery.getState());

            Map paraMap = new HashMap<String, String>();
            paraMap.put("pageSize", pageSize);
            paraMap.put("start", (pageIndex - 1) * pageSize);
            paraMap.put("orderBy", "id desc");
            paraMap.put("createidFuzzy", sysQuery.getCreateid());
            paraMap.put("keywordsFuzzy", sysQuery.getKeywords());
            paraMap.put("stateFuzzy", sysQuery.getState());       //过滤状态为-1
            int count = sysQueryService.selectCount(paraMap);
            List<SysQuery> sysQueryList = sysQueryService.selectList(paraMap);
            PagerModel result = new PagerModel(pageSize, pageIndex, count, sysQueryList);
            return result;
        } catch (Exception ex) {
            log.error("SysQueryController.getLists异常:" + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/sysquerysubmit", method = RequestMethod.POST)
    @ResponseBody
    public Object submit(SysQuery sysQuery, Model model) {
        try {
            sysQuery.setCreateid(loginInfo.getID());
            sysQuery.setState(0);       //状态为默认状态
            sysQuery = sysQueryService.update(sysQuery);
            model.addAttribute("sysQuery", sysQuery);
            return sysQuery;
        } catch (Exception ex) {
            log.error("SysQueryController.submit方法异常:" + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/sysqueryedit")
    public String edit(Model model, Integer id, String keywords, int type) {
        int temptype = type;
        try {
            SysQuery sysQuery = new SysQuery();
            if (id != null) {
                sysQuery = sysQueryService.selectById(id.intValue());
                temptype = sysQuery.getQuerytype();
            }
            Map<String, String> queryTypeMap = new HashMap<>();
            queryTypeMap.put("1", "指纹信息");
            queryTypeMap.put("2", "运营商信息");

            model.addAttribute("sysQuery", sysQuery);
            model.addAttribute("querytype", queryTypeMap);
            model.addAttribute("keywords", keywords);
            model.addAttribute("type", temptype);

//            model.addAttribute("state", mapState);
            return "ip/sysqueryedit";
        } catch (Exception ex) {
            log.error("SysQueryController.edit方法异常:" + ex.getMessage());
            return "";
        }
    }

    @RequestMapping(value = "/deletesysquery", method = RequestMethod.POST)
    @ResponseBody
    public Object deletesysquery(Model model, Integer id) {
        ReturnModel msg = new ReturnModel();
        if (id != null) {
            try {
                SysQuery sysQuery = sysQueryService.selectById(id.intValue());
                sysQuery.setState(-1);
                sysQueryService.update(sysQuery);
                msg.setMessage(sysQuery.getKeywords() + "删除成功");
                msg.setState(true);
            } catch (Exception ex) {
                msg.setState(false);
                msg.setMessage("没有找到删除的对象");
            }

        }
        return msg;
    }
//    @RequestMapping(value = "/sysqueryeditload")
//    @ResponseBody
//    public Object editLoad(Model model,Integer id){
//        try {
//            SysQuery sysQuery = new SysQuery();
//            if (id!=null){
//                sysQuery=sysQueryService.selectById(id.intValue());
//            }
//            Map<String, String> queryTypeMap = new HashMap<>();
//            queryTypeMap.put("1", "指纹信息");
//            queryTypeMap.put("2", "运营商信息");
//
//            Map<String, String> mapState = new HashMap<>();
//            mapState.put("-1", "删除");
//            mapState.put("0", "默认");
//            mapState.put("1", "作废");
//            mapState.put("2", "已截止");
//            model.addAttribute("sysQuery", sysQuery);
//            model.addAttribute("querytype", queryTypeMap);
//            model.addAttribute("state", mapState);
//            return sysQuery;
//        }
//        catch (Exception ex){
//            log.error("SysQueryController.edit方法异常:"+ex.getMessage());
//            return "";
//        }
//    }
}
