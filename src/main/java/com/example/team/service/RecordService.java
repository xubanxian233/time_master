package com.example.team.service;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.MonthRecord;
import com.example.team.pojo.TypeRecord;

import java.sql.Date;
import java.util.List;

public interface RecordService {
    void addRecordByUser(int userId);
    void updateRecordByUser(int userId);
    AccRecord getAccRecord(int userId);
    DailyRecord getDailyRecord(int userId, Date dailyDate);
    MonthRecord getMonthRecord(int userId,Date monthDate);
    List<TypeRecord> getTypeRecord(int userId);
    List<DailyRecord> listDailyRecordByMonth(int userId, Date litleMonthDate,Date bigMonthDate);
}
