package com.example.team.dao;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Transactional(rollbackFor = Exception.class)
public class BaseDAOImpl<T> implements BaseDAO<T> {

    @PersistenceContext
    public EntityManager entityManager;

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    private Class<T> clazz;

    public BaseDAOImpl() {
        super();
        this.clazz = null;
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void add(T obj) {
        getSession().save(obj);
    }

    @Override
    public void update(T obj) {
        getSession().merge(obj);
    }

    @Override
    public void delete(T obj) {
        getSession().delete(obj);
    }

    @Override
    public void delete(int objId) {
        Session session=getSession();
        session.delete(session.get(clazz,objId));
    }

    @Override
    public T get(Class<T> c, Serializable id) {
        return getSession().get(c,id);
    }
}
