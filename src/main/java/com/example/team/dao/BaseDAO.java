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
import java.io.Serializable;

@Transactional
public interface BaseDAO<T> {

    void add(T obj);

    void update(T obj);

    void delete(T obj);

    void delete(int objId);

    T get(Class<T> c, Serializable id);

}
