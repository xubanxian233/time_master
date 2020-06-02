package com.example.team.service;

import com.example.team.dao.*;
import com.example.team.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("recordService")
@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService {
    @Autowired
    private AccRecordDAO accRecordDAO;
    @Autowired
    private DailyRecordDAO dailyRecordDAO;
    @Autowired
    private MonthRecordDAO monthRecordDAO;

    private Date time;

    @Override
    public void addRecordByUser(int uId, int tTime, int tsId) {
        AccRecord accRecord = new AccRecord();
        accRecord.setUserId(uId);
        accRecord.setAcctime(tTime);
        if (tsId == 2) {
            accRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            accRecord.setFailCount(1);
        }
        accRecordDAO.add(accRecord);

        getCurrentTime();
        addmonthRecord(uId, tTime, tsId);
        adddailyRecord(uId, tTime, tsId);
    }

    public void getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        time = new Date(new java.util.Date().getTime());
    }

    @Override
    public void updateRecordByUser(int uId, int tTime, int tsId) {
        AccRecord accRecord = accRecordDAO.getByUserId(uId);
        accRecord.setAcctime(accRecord.getAcctime() + tTime);
        if (tsId == 2) {
            accRecord.setSuccessCount(accRecord.getSuccessCount() + 1);
        } else if (tsId == 3) {
            accRecord.setFailCount(accRecord.getFailCount() + 1);
        }
        accRecordDAO.update(accRecord);

        getCurrentTime();

        String t = time.toString();
        String month = t.substring(0, t.indexOf("-"));

        MonthRecord monthRecord = new MonthRecord();
        t = monthRecord.getMonthDate().toString();
        String m2 = t.substring(0, t.indexOf("-"));
        if (month.equals(m2)) {
            updatemonthRecord(uId, tTime, tsId);
        } else {
            addmonthRecord(uId, tTime, tsId);
        }

        if (dailyRecordDAO.getByUserId(uId, time) != null)
            updatedailyRecord(uId, tTime, tsId);
        else
            adddailyRecord(uId, tTime, tsId);
    }

    public void addmonthRecord(int uId, int tTime, int tsId) {
        MonthRecord monthRecord = new MonthRecord();
        monthRecord.setUserId(uId);
        monthRecord.setAcctime(tTime);
        monthRecord.setMonthDate(time);
        if (tsId == 2) {
            monthRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            monthRecord.setFailCount(1);
        }
        monthRecordDAO.add(monthRecord);
    }

    public void updatemonthRecord(int uId, int tTime, int tsId) {
        MonthRecord monthRecord = monthRecordDAO.getByUserId(uId, time);
        monthRecord.setAcctime(monthRecord.getAcctime() + tTime);
        if (tsId == 2) {
            monthRecord.setSuccessCount(monthRecord.getSuccessCount() + 1);
        } else if (tsId == 3) {
            monthRecord.setFailCount(monthRecord.getFailCount() + 1);
        }
        monthRecordDAO.update(monthRecord);
    }

    public void adddailyRecord(int uId, int tTime, int tsId) {
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.setUserId(uId);
        dailyRecord.setAcctime(tTime);
        if (tsId == 2) {
            dailyRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            dailyRecord.setFailCount(1);
        }
        dailyRecordDAO.add(dailyRecord);
    }

    public void updatedailyRecord(int uId, int tTime, int tsId) {
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord = dailyRecordDAO.getByUserId(uId, time);
        if (tsId == 2) {
            dailyRecord.setSuccessCount(dailyRecord.getSuccessCount() + 1);
        } else if (tsId == 3) {
            dailyRecord.setFailCount(dailyRecord.getFailCount() + 1);
        }
        dailyRecordDAO.update(dailyRecord);
    }

    @Override
    public AccRecord getAccRecord(int userId) {
        return accRecordDAO.getByUserId(userId);
    }

    @Override
    public DailyRecord getDailyRecord(int userId, Date dailyDate) {
        return dailyRecordDAO.getByUserId(userId, dailyDate);
    }

    @Override
    public MonthRecord getMonthRecord(int userId, Date monthDate) {
        return monthRecordDAO.getByUserId(userId, monthDate);
    }


    @Override
    public Map<Integer, Long> listDailyRecordByMonth(int userId, Date litleMonthDate, Date bigMonthDate) {
        List<DailyRecord> list = dailyRecordDAO.listDailyRecordByMonth(userId, litleMonthDate, bigMonthDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<Integer, Long> month = new HashMap<>();
        for (DailyRecord dailyRecord : list) {
            String date = simpleDateFormat.format(dailyRecord.getDailyDate());
            month.put(Integer.parseInt(date.substring(8, 10)), dailyRecord.getAcctime());
        }
        for (int i = 0; i < 31; i++) {
            if (!month.containsKey(i + 1)) {
                month.put(i + 1, 0L);
            }
        }
        return month;
    }
}
