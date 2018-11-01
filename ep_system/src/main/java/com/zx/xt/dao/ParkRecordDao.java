package com.zx.xt.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zx.xt.model.ParkRecord;


@Repository("parkRecordDao")
public interface ParkRecordDao {

	List<ParkRecord> selectList(Map<String, Object> paramMap);
    
	
}
