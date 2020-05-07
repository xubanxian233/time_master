package com.example.team.service;

import com.example.team.dao.*;
import com.example.team.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service("recordService")
@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService {
    @Autowired
    private AccRecordDAO accRecordDAO;
    @Autowired
    private DailyRecordDAO dailyRecordDAO;
    @Autowired
    private MonthRecordDAO monthRecordDAO;
    @Autowired
    private TypeRecordDAO typeRecordDAO;
    @Autowired
    private TypeDAO typeDAO;
    @Override
    public void addRecordByUser(int userId) {

    }

    @Override
    public void updateRecordByUser(int userId) {

    }

    @Override
    public AccRecord getAccRecord(int userId) {
        return accRecordDAO.getByUserId(userId);
    }

    @Override
    public DailyRecord getDailyRecord(int userId, Date dailyDate) {
        return dailyRecordDAO.getByUserId(userId,dailyDate);
    }

    @Override
    public MonthRecord getMonthRecord(int userId, Date monthDate) {
        return monthRecordDAO.getByUserId(userId,monthDate);
    }

    @Override
    public List<TypeRecord> getTypeRecord(int userId) {
        return typeRecordDAO.getByUserId(userId);
    }

    @Override
    public List<DailyRecord> listDailyRecordByMonth(int userId, Date litleMonthDate,Date bigMonthDate) {
        return dailyRecordDAO.listDailyRecordByMonth(userId,litleMonthDate,bigMonthDate);
    }
}
