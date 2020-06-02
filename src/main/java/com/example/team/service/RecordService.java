package com.example.team.service;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.MonthRecord;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface RecordService {

    void addAccRecord(int uId,long tTime,int tsId);
    void updateAccRecord(int uId,long tTime,int tsId);

    void addMonthRecord(int uId,long tTime,int tsId);
    void updateMonthRecord(int uId,long tTime,int tsId);

    void addDailyRecord(int uId,long tTime,int tsId);
    void updateDailyRecord(int uId,long tTime,int tsId);

    AccRecord getAccRecord(int userId);
    DailyRecord getDailyRecord(int userId, Date dailyDate);

    boolean isExistDailyRecord(int uid,Date time);
    boolean isExistMonthRecord(int uid,Date time);
    boolean isExistAccRecord(int uid);

    MonthRecord getMonthRecord(int userId, Date monthDate);
    Map<Integer, Long> listDailyRecordByMonth(int userId, Date litleMonthDate, Date bigMonthDate);
}
