package com.example.team.dao;

import com.example.team.pojo.DailyRecord;

import java.sql.Date;

public interface DailyRecordDAO {
    void add(DailyRecord dailyRecord);
    void delete(int dailyRecordId);
    void update(DailyRecord dailyRecord);
    DailyRecord getById(int dailyRecordId);
    DailyRecord getByUserId(int userId, Date dailyDate);
}
