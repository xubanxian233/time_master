package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.UserTodoSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from DailyRecord where dailyRecordId=:dailyRecordId";
        DailyRecord  dailyRecord = (DailyRecord) session.createQuery(hql).setParameter("dailyRecordId",dailyRecordId).uniqueResult();
        session.delete(dailyRecord);
        tx.commit();
        session.close();
    }

    @Override
    public void update(DailyRecord dailyRecord) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(dailyRecord);
        tx.commit();
        session.close();
    }

    @Override
    public DailyRecord getById(int dailyRecordId) {
        String hql="from DailyRecord where dailyRecordId=:dailyRecordId";
        return (DailyRecord) getSession().createQuery(hql).setParameter("dailyRecordId",dailyRecordId).uniqueResult();
    }

    @Override
    public DailyRecord getByUserId(int userId, Date dailyDate) {
        String hql="from DailyRecord where userId=:userId and dailyDate=:dailyDate";
        return (DailyRecord) getSession().createQuery(hql).setParameter("userId",userId).setParameter("dailyDate",dailyDate).uniqueResult();
    }

    @Override
    public List<DailyRecord> listDailyRecordByMonth(int userId, Date litleMonthDate, Date bigMonthDate){
        String hql = "from DailyRecord where userId=:userId and dailyDate>=:littleDate and dailyDate<=:bigDate ";
        return (List<DailyRecord>) getSession().createQuery(hql).setParameter("littleDate",litleMonthDate).setParameter("bigDate",bigMonthDate).list();
    }
}
