package com.example.team.dao;

import com.example.team.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;


@Repository(value = "userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(User user) {
        getSession().save(user);
        return user.getUserId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(int userId) {
        getSession().delete(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User user) {
        Session session=getSession();
        Transaction tx=session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
    }

    @Override
    public User getById(int userId) {
        String hql = "from User where userId=:userId";
        return (User) getSession().createQuery(hql).setParameter("userId", userId).uniqueResult();
    }

    @Override
    public User getByName(String name) {
        String hql = "from User where name=:name";
        return (User) getSession().createQuery(hql).setParameter("name", name).uniqueResult();
    }

    @Override
    public User getByTel(String tel) {
        String hql = "from User where tel=:tel";
        return (User) getSession().createQuery(hql).setParameter("tel", tel).uniqueResult();
    }

    @Override
    public User getByEmail(String email) {
        String hql = "from User where email=:email";
        return (User) getSession().createQuery(hql).setParameter("email", email).uniqueResult();
    }
}
