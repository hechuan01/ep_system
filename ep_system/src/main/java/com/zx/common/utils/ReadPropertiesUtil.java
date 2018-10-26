package com.zx.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 描述：${读取properits配置信息}
 *
 * @创建人： shuyizhi
 * @创建时间： 2017-03-20 13:58
 **/

public class ReadPropertiesUtil {
    private static Logger logger = LogManager.getLogger(ReadPropertiesUtil.class);

    public static Map<String, String> ReadProperits(String fileName) {
        Map<String, String> map = new HashMap<>();
        Properties properties = new Properties();
        try {
            String filePath =
                    ReadPropertiesUtil.class.getClassLoader().getResource("").getPath() + fileName;
            InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
            //加载属性列表
            properties.load(inputStream);
            Iterator<String> iterator = properties.stringPropertyNames().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                map.put(key, properties.getProperty(key));

            }
            inputStream.close();        //关闭流
        } catch (Exception ex) {
            logger.error("ReadPropertiesUtil.ReadPropertis方法异常:" + ex.getMessage());
        }
        return map;
    }
}
