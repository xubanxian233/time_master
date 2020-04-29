package com.example.team.dao;

import com.example.team.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;


@Repository(value = "userDAO")
@Transactional(rollbackFor = Exception.class)
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(User user) {
        getSession().save(user);
    }

    @Override
    public void delete(int userId) {
        getSession().delete(userId);
    }

    @Override
    public void update(User user) {
        getSession().update(user);
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
