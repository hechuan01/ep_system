package com.zx.xt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.constraints.Size;

import org.nutz.json.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zx.common.annotation.NotAuthPassport;
import com.zx.common.utils.PagerModel;
import com.zx.system.model.SysRole;
import com.zx.system.service.RoleService;
import com.zx.xt.model.ParkRecord;
import com.zx.xt.service.RecordService;


@Controller
@RequestMapping("/record")
public class RecordController {

	@Resource
    private RecordService recordService;
	
	
	/**
     * Method description
     *
     * @return
     */
	@NotAuthPassport
    @RequestMapping(value = "/parkRecord")
    public String parkRecord() {
    	
        return "record/parkRecord";
    }
    
    /**
     * Method description
     *
     * @param pageSize
     * @param pageIndex
     * @return
     * @throws JsonProcessingException
     */
    @NotAuthPassport
    @RequestMapping(value = "/selectList")
    @ResponseBody
    public Object selectList(Integer pageSize, Integer pageIndex) throws JsonProcessingException {
    	if (pageSize == null) {
            pageSize = 10;
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("start", (pageIndex - 1) * pageSize);
        paramMap.put("end", pageIndex * pageSize);

        List<ParkRecord> list = recordService.selectList(paramMap);
        return Json.toJson(list);
    }
	
	
	
	
}