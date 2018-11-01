package com.zx.xt.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.zx.xt.dao.ParkRecordDao;
import com.zx.xt.model.ParkRecord;
import com.zx.xt.service.RecordService;


@Service(value = "recordService")
public class RecordServiceImpl implements RecordService {
    private static Logger logger = LogManager.getLogger(RecordServiceImpl.class);
    
    @Resource(name = "parkRecordDao")
    private ParkRecordDao parkRecordDao;

	@Override
	public List<ParkRecord> selectList(Map<String, Object> paramMap) {
		
		return parkRecordDao.selectList(paramMap);
	}
    
   
    
}
