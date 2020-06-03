package com.example.team.util;

import java.sql.Date;
import java.util.Calendar;

public class DateUtil {
    /**
     * @description: 获取当月日期
     * @Param:
     * @return:
     * @update: time: 2020/6/3 9:37
     */
    public static Date getMonthDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDate(calendar);
    }

    /**
     * @description: 获取当日日期
     * @Param:
     * @return:
     * @update: time: 2020/6/3 9:37
     */
    public static Date getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        return getDate(calendar);
    }

    public static Date getDate(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        java.util.Date date = calendar.getTime();
        return new Date(date.getTime());
    }
}
