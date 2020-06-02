package com.example.team.service;

import com.example.team.dao.*;
import com.example.team.pojo.*;
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
    private Date time;
    private Date mTime;
    private int days;

    private void getMonthDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        java.util.Date date=calendar.getTime();
        mTime=new Date(date.getTime());
    }

    @Override
    public void addRecordByUser(int uId,long tTime,int tsId) {
        AccRecord accRecord=new AccRecord();
        accRecord.setUserId(uId);
        accRecord.setAcctime(tTime);
        Date date=userDAO.getById(uId).getCreate();
        getCurrentTime();
        days = (int) ((time.getTime() - date.getTime())/(1000*3600*24));
        accRecord.setDailytime(tTime/days);
        if(tsId==2){
            accRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            accRecord.setFailCount(1);
        }
        accRecordDAO.add(accRecord);
        addmonthRecord(uId,tTime,tsId);
        adddailyRecord(uId,tTime,tsId);
    }

    public void getCurrentTime(){
//
//        Calendar cal=Calendar.getInstance();
//        int y=cal.get(Calendar.YEAR);
//        int m=cal.get(Calendar.MONTH);
//        int d=cal.get(Calendar.DATE);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        time= Date.valueOf(sdf.format(new Date(y,m,d)));
        java.util.Date date = new java.util.Date();
        time=new Date(date.getTime());
    }

    @Override
    public void updateRecordByUser(int uId,long tTime,int tsId) {
        getCurrentTime();
        AccRecord accRecord=accRecordDAO.getByUserId(uId);
        accRecord.setAcctime(accRecord.getAcctime()+tTime);
        days = (int) ((time.getTime() - userDAO.getById(uId).getCreate().getTime()) / (1000*3600*24));
        accRecord.setDailytime(accRecord.getAcctime()/days);
        if(tsId==2){
            accRecord.setSuccessCount(accRecord.getSuccessCount()+1);
        }else if (tsId==3){
            accRecord.setFailCount(accRecord.getFailCount()+1);
        }
        accRecordDAO.update(accRecord);

        getMonthDate();
        if(monthRecordDAO.getByUserId(uId,mTime)!=null){
            updatemonthRecord(uId,tTime,tsId);
        }
        else{
            addmonthRecord(uId,tTime,tsId);
        }

        if (dailyRecordDAO.getByUserId(uId, time) != null)
            updatedailyRecord(uId, tTime, tsId);
        else
            adddailyRecord(uId, tTime, tsId);
    }

    public void addmonthRecord(int uId,long tTime,int tsId){
        MonthRecord monthRecord=new MonthRecord();
        monthRecord.setUserId(uId);
        monthRecord.setAcctime(tTime);
        getMonthDate();
        monthRecord.setMonthDate(mTime);
        if(tsId==2){
            monthRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            monthRecord.setFailCount(1);
        }
        monthRecordDAO.add(monthRecord);
    }

    public void updatemonthRecord(int uId,long tTime,int tsId){
        getMonthDate();
        MonthRecord monthRecord=monthRecordDAO.getByUserId(uId,mTime);
        monthRecord.setAcctime(monthRecord.getAcctime()+tTime);
        if(tsId==2){
            monthRecord.setSuccessCount(monthRecord.getSuccessCount()+1);
        }else if (tsId==3){
            monthRecord.setFailCount(monthRecord.getFailCount()+1);
        }
        monthRecordDAO.update(monthRecord);
    }

    public void adddailyRecord(int uId,long tTime,int tsId){
        DailyRecord dailyRecord=new DailyRecord();
        dailyRecord.setUserId(uId);
        dailyRecord.setAcctime(tTime);
        getCurrentTime();
        dailyRecord.setDailyDate(time);
        if(tsId==2){
            dailyRecord.setSuccessCount(1);
        } else if (tsId == 3) {
            dailyRecord.setFailCount(1);
        }
        dailyRecordDAO.add(dailyRecord);
    }

    public void updatedailyRecord(int uId,long tTime,int tsId){
        DailyRecord dailyRecord=new DailyRecord();
        dailyRecord=dailyRecordDAO.getByUserId(uId,time);
        if(tsId==2){
            dailyRecord.setSuccessCount(dailyRecord.getSuccessCount()+1);
        }else if (tsId==3){
            dailyRecord.setFailCount(dailyRecord.getFailCount()+1);
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

    @Override
    public boolean isExistDailyRecord(int uid,Date time){
        if(dailyRecordDAO.getByUserId(uid, time)!=null){
            return true;
        }else{
            return false;
        }
    }
}
