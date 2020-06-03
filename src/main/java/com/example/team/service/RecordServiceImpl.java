package com.example.team.service;

import com.example.team.dao.*;
import com.example.team.pojo.*;
import com.example.team.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.sql.Date;
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
    @Autowired
    private UserDAO userDAO;


    @Override
    /**
     * @description: 添加累计记录
     * @Param: [uId, tTime, tsId]
     * @return: void
     * @update: time: 2020/6/3 9:37
     */
    public void addAccRecord(int uId, long tTime, int tsId) {
        AccRecord accRecord = new AccRecord();
        accRecord.setUserId(uId);
        accRecord.setAcctime(tTime);
        Date date = userDAO.get(User.class, uId).getCreate();
        int days = (int) ((DateUtil.getCurrentTime().getTime() - date.getTime()) / (1000 * 3600 * 24));
        accRecord.setDailytime(tTime / days);
        if (tsId == 2) {
            accRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            accRecord.setFailCount(1);
        }
        accRecordDAO.add(accRecord);
    }

    @Override
    /**
     * @description: 更新累计记录
     * @Param: [uId, tTime, tsId]
     * @return: void
     * @update: time: 2020/6/3 9:37
     */
    public void updateAccRecord(int uId, long tTime, int tsId) {
        AccRecord accRecord = accRecordDAO.getByUserId(uId);
        accRecord.setAcctime(accRecord.getAcctime() + tTime);
        int days =
                (int) ((DateUtil.getCurrentTime().getTime() - userDAO.get(User.class, uId).getCreate().getTime()) / (1000 * 3600 * 24)) + 1;
        accRecord.setDailytime(accRecord.getAcctime() / days);
        if (tsId == 2) {
            accRecord.setSuccessCount(accRecord.getSuccessCount() + 1);
        } else if (tsId == 3) {
            accRecord.setFailCount(accRecord.getFailCount() + 1);
        }
        accRecordDAO.update(accRecord);
    }

    @Override
    /**
     * @description: 添加月记录
     * @Param: [uId, tTime, tsId]
     * @return: void
     * @update: time: 2020/6/3 9:37
     */
    public void addMonthRecord(int uId, long tTime, int tsId) {
        MonthRecord monthRecord = new MonthRecord();
        monthRecord.setUserId(uId);
        monthRecord.setAcctime(tTime);
        monthRecord.setMonthDate(DateUtil.getMonthDate());
        if (tsId == 2) {
            monthRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            monthRecord.setFailCount(1);
        }
        monthRecordDAO.add(monthRecord);
    }

    @Override
    /**
     * @description: 更新月记录
     * @Param: [uId, tTime, tsId]
     * @return: void
     * @update: time: 2020/6/3 9:37
     */
    public void updateMonthRecord(int uId, long tTime, int tsId) {
        MonthRecord monthRecord = monthRecordDAO.getByUserId(uId, DateUtil.getMonthDate());
        monthRecord.setAcctime(monthRecord.getAcctime() + tTime);
        if (tsId == 2) {
            monthRecord.setSuccessCount(monthRecord.getSuccessCount() + 1);
        } else if (tsId == 3) {
            monthRecord.setFailCount(monthRecord.getFailCount() + 1);
        }
        monthRecordDAO.update(monthRecord);
    }

    @Override
    /**
     * @description:
     * @Param: [uId, tTime, tsId]
     * @return: void
     * @update: time: 2020/6/3 9:37
     */
    public void addDailyRecord(int uId, long tTime, int tsId) {
        DailyRecord dailyRecord = new DailyRecord();
        dailyRecord.setUserId(uId);
        dailyRecord.setAcctime(tTime);
        dailyRecord.setDailyDate(DateUtil.getCurrentTime());
        if (tsId == 2) {
            dailyRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            dailyRecord.setFailCount(1);
        }
        dailyRecordDAO.add(dailyRecord);
    }

    @Override
    /**
     * @description:
     * @Param: [uId, tTime, tsId]
     * @return: void
     * @update: time: 2020/6/3 9:37
     */
    public void updateDailyRecord(int uId, long tTime, int tsId){
        DailyRecord dailyRecord = dailyRecordDAO.getByUserId(uId, DateUtil.getCurrentTime());
        if (tsId == 2) {
            dailyRecord.setSuccessCount(dailyRecord.getSuccessCount() + 1);
        } else if (tsId == 3) {
            dailyRecord.setFailCount(dailyRecord.getFailCount() + 1);
        }
        dailyRecord.setAcctime(dailyRecord.getAcctime() + tTime);
        dailyRecordDAO.update(dailyRecord);
    }

    @Override
    /**
     * @description:
     * @Param: [userId]
     * @return: com.example.team.pojo.AccRecord
     * @update: time: 2020/6/3 9:37
     */
    public AccRecord getAccRecord(int userId) {
        return accRecordDAO.getByUserId(userId);
    }

    @Override
    /**
     * @description:
     * @Param: [userId, dailyDate]
     * @return: com.example.team.pojo.DailyRecord
     * @update: time: 2020/6/3 9:37
     */
    public DailyRecord getDailyRecord(int userId, Date dailyDate) {
        return dailyRecordDAO.getByUserId(userId, dailyDate);
    }

    @Override
    /**
     * @description:
     * @Param: [userId, monthDate]
     * @return: com.example.team.pojo.MonthRecord
     * @update: time: 2020/6/3 9:37
     */
    public MonthRecord getMonthRecord(int userId, Date monthDate) {
        return monthRecordDAO.getByUserId(userId, monthDate);
    }


    @Override
    /**
     * @description:
     * @Param: [userId, litleMonthDate, bigMonthDate]
     * @return: java.util.Map<java.lang.Integer, java.lang.Long>
     * @update: time: 2020/6/3 9:37
     */
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

    //判断是否存在记录
    @Override
    /**
     * @description:
     * @Param: [uid, time]
     * @return: boolean
     * @update: time: 2020/6/3 9:37
     */
    public boolean isExistDailyRecord(int uid, Date time) {
        if (dailyRecordDAO.getByUserId(uid, time) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * @description:
     * @Param: [uid, time]
     * @return: boolean
     * @update: time: 2020/6/3 9:37
     */
    public boolean isExistMonthRecord(int uid, Date time) {
        if (monthRecordDAO.getByUserId(uid, time) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * @description:
     * @Param: [uid]
     * @return: boolean
     * @update: time: 2020/6/3 9:37
     */
    public boolean isExistAccRecord(int uid) {
        if (accRecordDAO.getByUserId(uid) != null) {
            return true;
        } else {
            return false;
        }
    }
}
