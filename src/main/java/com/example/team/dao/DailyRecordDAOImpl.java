package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

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
    public DailyRecord getByUserId(int userId) {
        String hql="from DailyRecord where userId=:userId";
        return (DailyRecord) getSession().createQuery(hql).setParameter("userId",userId).uniqueResult();
    }
}
