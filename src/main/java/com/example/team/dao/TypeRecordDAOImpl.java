package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.TypeRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("typeRecordDAO")
@Transactional(rollbackFor = Exception.class)
public class TypeRecordDAOImpl implements TypeRecordDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(TypeRecord typeRecord) {
        getSession().save(typeRecord);
    }

    @Override
    public void delete(int typeRecordId) {
        getSession().delete(typeRecordId);
    }

    @Override
    public void update(TypeRecord typeRecord) {
        getSession().update(typeRecord);
    }

    @Override
    public TypeRecord getById(int typeRecordId) {
        String hql = "from TypeRecord where typeRecordId=:typeRecordId";
        return (TypeRecord) getSession().createQuery(hql).setParameter("typeRecordId", typeRecordId).uniqueResult();
    }

    @Override
    public List<TypeRecord> getByUserId(int userId) {
        String hql = "from TypeRecord where userId=:userId";
        return getSession().createQuery(hql).setParameter("userId", userId).list();
    }
}
