package com.example.team.dao;

import com.example.team.pojo.MonthRecord;

import java.sql.Date;

public interface MonthRecordDAO extends BaseDAO<MonthRecord>{

    MonthRecord getByUserId(int userId, Date monthDate);
}
