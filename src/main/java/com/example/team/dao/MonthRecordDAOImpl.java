package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.MonthRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository("monthRecordDAO")
public class MonthRecordDAOImpl implements MonthRecordDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(MonthRecord monthRecord) {
        getSession().save(monthRecord);
    }

    @Override
    public void delete(int monthRecordId) {
        getSession().delete(monthRecordId);
    }

    @Override
    public void update(MonthRecord monthRecord) {
        getSession().update(monthRecord);
    }

    @Override
    public MonthRecord getById(int monthRecordId) {
        String hql="from MonthRecord where monthRecordId=:monthRecordId";
        return (MonthRecord) getSession().createQuery(hql).setParameter("monthRecordId",monthRecordId).uniqueResult();
    }

    @Override
    public MonthRecord getByUserId(int userId) {
        String hql="from MonthRecord where userId=:userId";
        return (MonthRecord) getSession().createQuery(hql).setParameter("userId",userId).uniqueResult();
    }
}
