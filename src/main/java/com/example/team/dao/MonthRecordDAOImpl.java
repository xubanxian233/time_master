package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.MonthRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.sql.Date;

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
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from monthrecord where month_record_id=:monthRecordId";
        MonthRecord monthRecord = (MonthRecord) session.createQuery(hql).setParameter("month_record_id",monthRecordId).uniqueResult();
        session.delete(monthRecord);
        tx.commit();
        session.close();
    }

    @Override
    public void update(MonthRecord monthRecord) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(monthRecord);
        tx.commit();
        session.close();
    }

    @Override
    public MonthRecord getById(int monthRecordId) {
        String hql="from MonthRecord where monthRecordId=:monthRecordId";
        return (MonthRecord) getSession().createQuery(hql).setParameter("monthRecordId",monthRecordId).uniqueResult();
    }

    @Override
    public MonthRecord getByUserId(int userId, Date monthDate) {
        String hql="from MonthRecord where userId=:userId and month_date=:monthDate";
        return (MonthRecord) getSession().createQuery(hql).setParameter("userId",userId).uniqueResult();
    }
}