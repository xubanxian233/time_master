package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository("accRecordDAO")
public class AccRecordDAOImpl implements AccRecordDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }
    @Override
    public void add(AccRecord accRecord) {
        getSession().save(accRecord);
    }

    @Override
    public void delete(int accRecordId) {
        getSession().delete(accRecordId);
    }

    @Override
    public void update(AccRecord accRecord) {
        getSession().update(accRecord);
    }

    @Override
    public AccRecord getById(int accRecordId) {
        String hql="from AccRecord where accRecordId=:accRecordId";
        return (AccRecord) getSession().createQuery(hql).setParameter("accRecordId",accRecordId).uniqueResult();
    }

    @Override
    public AccRecord getByUserId(int userId) {
        String hql="from AccRecord where userId=:userId";
        return (AccRecord) getSession().createQuery(hql).setParameter("userId",userId).uniqueResult();
    }
}
