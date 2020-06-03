package com.example.team.dao;

import com.example.team.pojo.DailyRecord;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public interface DailyRecordDAO extends BaseDAO<DailyRecord>{

    DailyRecord getByUserId(int userId, Date dailyDate);

    List<DailyRecord> listDailyRecordByMonth(int userId, Date litleMonthDate, Date bigMonthDate);
}
