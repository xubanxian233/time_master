package com.example.team.dao;

import com.example.team.pojo.UserTodoSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository(value = "UserTodoSetDAO")
@Transactional(rollbackFor = Exception.class)
public class UserTodoSetDAOImpl implements UserTodoSetDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(UserTodoSet userTodoSet) {
        getSession().save(userTodoSet);
    }

    @Override
    public void update(UserTodoSet userTodoSet) {
        getSession().update(userTodoSet);
    }

    @Override
    public void delete(int userTodoSetId) {
        getSession().delete(userTodoSetId);
    }

    @Override
    public UserTodoSet getById(int userTodoSetId) {
        String hql = "from UserTodoSet where userTodoSetId=:userTodoSetId";
        return (UserTodoSet) getSession().createQuery(hql).setParameter("userTodoSetId",userTodoSetId).uniqueResult();
    }

    @Override
    public List<UserTodoSet> listById(int userId) {
        String hql = "from UserTodoSet where userId=:userId";
        return (List<UserTodoSet>) getSession().createQuery(hql).setParameter("userId",userId).list();
    }
}
