package com.example.team.dao;

import com.example.team.pojo.DailyRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@Repository("dailyRecordDAO")
public class DailyRecordDAOImpl implements DailyRecordDAO {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void add(DailyRecord dailyRecord) {
        getSession().save(dailyRecord);
    }

    @Override
    public void delete(int dailyRecordId) {
        /*Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from dailyrecord where daily_record_id=:dailyRecordId";
        DailyRecord  dailyRecord = (DailyRecord) session.createQuery(hql).setParameter("daily_record_id",dailyRecordId).uniqueResult();
        session.delete(dailyRecord);
        tx.commit();
        session.close();*/
    }

    @Override
    public void update(DailyRecord dailyRecord) {
        getSession().merge(dailyRecord);
    }

    @Override
    public DailyRecord getById(int dailyRecordId) {
        String hql = "from DailyRecord where dailyRecordId=:dailyRecordId";
        return (DailyRecord) getSession().createQuery(hql).setParameter("dailyRecordId", dailyRecordId).uniqueResult();
    }

    @Override
    public DailyRecord getByUserId(int userId, Date dailyDate) {
        Date date=new Date(dailyDate.getTime()+10*60*60*1000);
        String hql = "from DailyRecord where userId=:userId and dailyDate>=:dailyDate and dailyDate<=:date";
        return (DailyRecord) getSession().createQuery(hql)
                .setParameter("userId", userId)
                .setParameter("dailyDate",dailyDate)
                .setParameter("date",date)
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
