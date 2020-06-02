package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.MonthRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.sql.Date;

@Repository("monthRecordDAO")
@Transactional(rollbackFor = Exception.class)
public class MonthRecordDAOImpl implements MonthRecordDAO {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void add(MonthRecord monthRecord) {
        getSession().save(monthRecord);
    }

    @Override
    public void delete(int monthRecordId) {
       /* Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from MonthRecord where monthRecordId=:monthRecordId";
        MonthRecord monthRecord = (MonthRecord) session.createQuery(hql).setParameter("monthRecordId",monthRecordId).uniqueResult();
        session.delete(monthRecord);
        tx.commit();
        session.close();*/
    }

    @Override
    public void update(MonthRecord monthRecord) {
        getSession().merge(monthRecord);
    }

    @Override
    public MonthRecord getById(int monthRecordId) {
        String hql = "from MonthRecord where monthRecordId=:monthRecordId";
        return (MonthRecord) getSession().createQuery(hql).setParameter("monthRecordId", monthRecordId).uniqueResult();
    }

    @Override
    public MonthRecord getByUserId(int userId, Date monthDate) {
        String hql = "from MonthRecord where userId=:userId and monthDate=:monthDate";
        return (MonthRecord) getSession().createQuery(hql).setParameter("userId", userId).setParameter("monthDate",
                monthDate).uniqueResult();
    }
}
