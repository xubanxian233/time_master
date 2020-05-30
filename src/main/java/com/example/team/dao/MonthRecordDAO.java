package com.example.team.dao;

import com.example.team.pojo.MonthRecord;

import java.sql.Date;

public interface MonthRecordDAO {
    void add(MonthRecord monthRecord);

    void delete(int monthRecordId);

    void update(MonthRecord monthRecord);

    MonthRecord getById(int monthRecordId);

    MonthRecord getByUserId(int userId, Date monthDate);
}
