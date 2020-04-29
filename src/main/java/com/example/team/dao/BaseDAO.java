package com.example.team.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class BaseDAO extends HibernateDaoSupport {
    /*@Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }*/
}
