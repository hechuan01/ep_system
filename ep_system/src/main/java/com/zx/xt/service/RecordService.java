package com.zx.xt.service;

import java.util.List;
import java.util.Map;

import com.zx.xt.model.ParkRecord;


public interface RecordService {

	List<ParkRecord> selectList(Map<String, Object> paramMap);
    
	
}
