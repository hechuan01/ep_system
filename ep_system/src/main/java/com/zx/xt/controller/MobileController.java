package com.zx.xt.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.json.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.common.annotation.NotAuthPassport;
import com.zx.common.icbc.EICBCUtil;
import com.zx.xt.model.RecordInfo;
import com.zx.xt.model.eicbc.AccessToken;
import com.zx.xt.model.eicbc.Card;
import com.zx.xt.model.eicbc.CardList;
import com.zx.xt.model.eicbc.User;

@Controller
@RequestMapping("/mobile")
public class MobileController {

	private double cost = 10.5;
	
	@NotAuthPassport
	@RequestMapping(value="/sign")
	public String sign(HttpServletRequest request){
		String code = request.getParameter("code");
		
		EICBCUtil tokenUtil = new EICBCUtil();
		AccessToken token = tokenUtil.getAccessToken(code);
		System.out.println(token.getAccess_token());
		User user = tokenUtil.getUser(token);
		
		System.out.println(user.getMobileno()+"-----------"+user.getNickname());
		CardList cardList = tokenUtil.getCardList(token, user);
		
		List<Card> list = cardList.getiProList();
		if(list !=null){
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getCardNo());
			}
		}
		
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
