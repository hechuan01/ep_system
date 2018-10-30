package com.zx.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wang on 2017/3/27.
 */
public class DateUtil {
    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date dayEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }
    
    /**
     * 比较两个时间相差的小时数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getHourBetweenTwoDate(Date startDate, Date endDate) {
        int hour = 0;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        
        hour = ((int) (startCalendar.getTime().getTime() / 1000) - (int) (endCalendar.getTime().getTime() / 1000)) / (60 * 60);
 
        return hour;
    }
}
