package com.example.team.dao;

import com.example.team.pojo.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("typeDAO")
@Transactional(rollbackFor = Exception.class)
public class TypeDAOImpl implements TypeDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public List<Type> listType() {
        return getSession().createQuery("from Type").list();
    }
}
