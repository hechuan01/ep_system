package com.zx.xt.controller;

import com.zx.common.controller.BaseController;
import com.zx.xt.model.SearchLog;
import com.zx.xt.service.SearchLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 描述：${searchLog Controller}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-27 14:57
 **/
@Controller
@RequestMapping(value = "/searchlog")
public class SearchLogController extends BaseController {


    private static Logger logger = LogManager.getLogger(SearchLogController.class);
    @Resource(name = "searchLogService")
    private SearchLogService searchLogService;

    @RequestMapping(value = "/getlist")
    @ResponseBody
    public Object getLists(String date) {
        try {
            Map map = new HashMap<String, String>();
            map.put("createtime", date);
            List<SearchLog> searchLogList = searchLogService.selectList(map);
            return searchLogList;
        } catch (Exception ex) {
            logger.error("SearchLogController.getLists方法异常:" + ex.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/selectCount")
    @ResponseBody
    public List<Map<String, Object>> selectCount(String createtimestart, String createtimeend) {
        try {
            Map map = new HashMap<String, String>();
            map.put("createtimestart", createtimestart);
            map.put("createtimeend", createtimeend);
            List<Map<String, Object>> resultMap = searchLogService.selectCount(map);
            return resultMap;
        } catch (Exception ex) {
            logger.error("SearchLogController.selectCount方法异常：" + ex.getMessage());
        }
        return null;
    }
}
