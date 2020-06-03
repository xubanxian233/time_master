package com.example.team.dao;

import com.example.team.pojo.DailyRecord;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository("dailyRecordDAO")
public class DailyRecordDAOImpl extends BaseDAOImpl<DailyRecord> implements DailyRecordDAO {

    @Override
    public DailyRecord getByUserId(int userId, Date dailyDate){
        //Date date=new Date(dailyDate.getTime()+24*60*60*1000);
        String hql = "from DailyRecord where userId=:userId and dailyDate=:dailyDate";
        return (DailyRecord) getSession().createQuery(hql)
                .setParameter("userId", userId)
                .setParameter("dailyDate",dailyDate)
                .uniqueResult();
    }

    public List listDailyRecordByMonth(int userId, Date litleMonthDate, Date bigMonthDate) {
        String hql = "from DailyRecord where userId=:userId and dailyDate>=:litleMonthDate and dailyDate<=:bigMonthDate";
        return getSession().createQuery(hql)
                .setParameter("userId", userId)
                .setParameter("litleMonthDate", litleMonthDate)
                .setParameter("bigMonthDate", bigMonthDate)
                .list();
    }
}
