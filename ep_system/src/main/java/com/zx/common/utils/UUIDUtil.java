package com.zx.common.utils;

import java.util.UUID;

/**
 * uuid工具类
 * @author zhang
 *
 */
public class UUIDUtil {

	/** 
	* 获得一个UUID 
	* @return String UUID 
	*/ 
	public static String getUUID(){ 
		String uuid = UUID.randomUUID().toString(); 
		return uuid.replaceAll("-", "");
	}
}
