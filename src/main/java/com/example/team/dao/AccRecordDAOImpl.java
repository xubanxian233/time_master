package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.UserTodoSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository("accRecordDAO")
@Transactional(rollbackFor = Exception.class)
public class AccRecordDAOImpl implements AccRecordDAO {
    /*@Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        //return entityManagerFactory.unwrap(SessionFactory.class).openSession();
        return entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
    }*/

    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void add(AccRecord accRecord) {
        getSession().save(accRecord);
    }

    @Override
    public void delete(int accRecordId) {
        /*Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from accrecord where acc_record_id=:accRecordId";
        AccRecord accRecord = (AccRecord) session.createQuery(hql).setParameter("acc_record_id", accRecordId).uniqueResult();
        session.delete(accRecord);
        tx.commit();
        session.close();*/
    }

    @Override
    public void update(AccRecord accRecord) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.merge(accRecord);
        tx.commit();
        session.close();
    }

    @Override
    public AccRecord getById(int accRecordId) {
        String hql = "from AccRecord where accRecordId=:accRecordId";
        return (AccRecord) getSession().createQuery(hql).setParameter("accRecordId", accRecordId).uniqueResult();
    }

    @Override
    public AccRecord getByUserId(int userId) {
        String hql = "from AccRecord where userId=:userId";
        return (AccRecord) getSession().createQuery(hql).setParameter("userId", userId).uniqueResult();
    }
}
