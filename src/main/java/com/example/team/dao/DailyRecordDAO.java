package com.example.team.dao;

import com.example.team.pojo.DailyRecord;

import java.sql.Date;
import java.util.List;

public interface DailyRecordDAO {
    void add(DailyRecord dailyRecord);
    void delete(int dailyRecordId);
    void update(DailyRecord dailyRecord);
    DailyRecord getById(int dailyRecordId);
    DailyRecord getByUserId(int userId,Date dailyDate);
    List<DailyRecord> listDailyRecordByMonth(int userId, Date litleMonthDate,Date bigMonthDate);
}
