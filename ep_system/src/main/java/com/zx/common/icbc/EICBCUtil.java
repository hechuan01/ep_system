package com.zx.common.icbc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.nutz.json.Json;

import com.zx.common.utils.HttpClientUtils;
import com.zx.common.utils.PropertyUtil;
import com.zx.xt.model.eicbc.AccessToken;
import com.zx.xt.model.eicbc.CardList;
import com.zx.xt.model.eicbc.User;

/**
 * 调用融e联接口类
 * @author zhang
 *
 */
public class EICBCUtil {

	private static HttpClientUtils httpClientUtils = null;
	
	public EICBCUtil() {
		super();
		httpClientUtils = new HttpClientUtils();
	}

	/**
	 * 获取AccessToken
	 * @param code 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public synchronized AccessToken getAccessToken(String code){
		String appid = PropertyUtil.getProperty("appid");
		String url = PropertyUtil.getProperty("accessToken");
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			map.put("appid", URLEncoder.encode(appid,"utf-8"));
			map.put("code", URLEncoder.encode(code,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = httpClientUtils.doGet(url, map);
		AccessToken token = Json.fromJson(AccessToken.class, result);
		return token;
	}
	
	/**
	 * 获取融e联用户
	 * @param token
	 * @return
	 */
	@SuppressWarnings("static-access")
	public User getUser(AccessToken token){
		String url = PropertyUtil.getProperty("userInfo");
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			map.put("access_token", URLEncoder.encode(token.getAccess_token(),"utf-8"));
			map.put("openid", URLEncoder.encode(token.getOpenid(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = httpClientUtils.doGet(url, map);
		User user = Json.fromJson(User.class, result);
		return user;
	}
	
	/**
	 * 获取用户银行卡接口
	 * @param token
	 * @param user
	 * @return
	 */
	@SuppressWarnings("static-access")
	public CardList getCardList(AccessToken token, User user){
		String url = PropertyUtil.getProperty("cardList");
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			map.put("access_token", URLEncoder.encode(token.getAccess_token(),"utf-8"));
			map.put("openid", URLEncoder.encode(token.getOpenid(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(user.getCisno() == null || "null".equals(user.getCisno())){
			map.put("UserType", "2");
			map.put("MobileNo", user.getMobileno());
		}else{
			map.put("UserType", "1");
			map.put("MobileNo", user.getCisno());
		}
		String result = httpClientUtils.doPost(url, map);
		CardList cardList = Json.fromJson(CardList.class, result);
		return cardList;
	}
	
	
	
	
	
	
	
	
	
	
}
