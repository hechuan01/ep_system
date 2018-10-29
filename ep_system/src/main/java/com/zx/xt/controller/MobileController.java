package com.zx.xt.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.json.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.common.annotation.NotAuthPassport;
import com.zx.xt.model.RecordInfo;

@Controller
@RequestMapping("/mobile")
public class MobileController {

	private double cost = 10.5;
	
	@NotAuthPassport
	@RequestMapping(value="/sign")
	public String sign(){
		return "mobilePhone/sign";
	}
	
	@NotAuthPassport
	@RequestMapping(value="/selectRecord")
	public String selectRecord(){
		return "mobilePhone/selectRecord";
	}
	
	@NotAuthPassport
	@RequestMapping(value="/recordList")
	@ResponseBody
	public String recordList(){
		List<RecordInfo> list = new ArrayList<RecordInfo>();
		for (int i = 0; i < 10; i++) {
			RecordInfo ri = new RecordInfo();
			ri.setCarNumber("皖A·234234");
			ri.setParkDate(new Date());
			ri.setCost(cost);
			list.add(ri);
			cost = add(ri.getCost(), 1.0);
		}
 		return Json.toJson(list);
	}
	
	 public static double add(double v1, double v2){
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.add(b2).doubleValue();
	 }
}
