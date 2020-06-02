package com.example.team.service;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.MonthRecord;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface RecordService {
    void addRecordByUser(int uId, int tTime, int tsId);

    void updateRecordByUser(int uId, int tTime, int tsId);

    AccRecord getAccRecord(int userId);

    DailyRecord getDailyRecord(int userId, Date dailyDate);

    MonthRecord getMonthRecord(int userId, Date monthDate);

    Map<Integer, Long> listDailyRecordByMonth(int userId, Date litleMonthDate, Date bigMonthDate);
}
