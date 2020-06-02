package com.example.team.service;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.MonthRecord;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface RecordService {

    void addaccRecord(int uId,long tTime,int tsId);
    void updateaccRecord(int uId,long tTime,int tsId);

    void addmonthRecord(int uId,long tTime,int tsId);
    void updatemonthRecord(int uId,long tTime,int tsId);

    void adddailyRecord(int uId,long tTime,int tsId);
    void updatedailyRecord(int uId,long tTime,int tsId);

    AccRecord getAccRecord(int userId);
    DailyRecord getDailyRecord(int userId, Date dailyDate);

    boolean isExistDailyRecord(int uid,Date time);
    boolean isExistMonthRecord(int uid,Date time);
    boolean isExistAccRecord(int uid);

    MonthRecord getMonthRecord(int userId, Date monthDate);
    Map<Integer, Long> listDailyRecordByMonth(int userId, Date litleMonthDate, Date bigMonthDate);
}
