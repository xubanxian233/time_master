package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.sql.Date;
import java.util.List;

@Repository("dailyRecordDAO")
public class DailyRecordDAOImpl implements DailyRecordDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(DailyRecord dailyRecord) {
        getSession().save(dailyRecord);
    }

    @Override
    public void delete(int dailyRecordId) {
        getSession().delete(dailyRecordId);
    }

    @Override
    public void update(DailyRecord dailyRecord) {
        getSession().update(dailyRecord);
    }

    @Override
    public DailyRecord getById(int dailyRecordId) {
        String hql="from DailyRecord where dailyRecordId=:dailyRecordId";
        return (DailyRecord) getSession().createQuery(hql).setParameter("dailyRecordId",dailyRecordId).uniqueResult();
    }

    @Override
    public DailyRecord getByUserId(int userId, Date dailyDate) {
        String hql="from DailyRecord where userId=:userId and dailyDate=:dailyDate";
        return (DailyRecord) getSession().createQuery(hql).setParameter("userId", userId)
                .setParameter("dailyDate", dailyDate).uniqueResult();
    }

    @Override
    public List<DailyRecord> listDailyRecordByMonth(int userId, Date litleMonthDate,Date bigMonthDate) {
        String hql="from DailyRecord where userId=:userId and dailyDate>=:litleMonthDate and dailyDate<=:bigMonthDate";
        return getSession().createQuery(hql).setParameter("userId", userId)
                .setParameter("litleMonthDate", litleMonthDate)
                .setParameter("bigMonthDate",bigMonthDate).list();
    }
}
