package com.example.team.dao;

import com.example.team.pojo.UserTodo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository(value = "userTodoDAO")
@Transactional(rollbackFor = Exception.class)
public class UserTodoDAOImpl implements UserTodoDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(UserTodo userTodo) {
        getSession().save(userTodo);
    }

    @Override
    public void delete(int userTodoId) {
        getSession().delete(userTodoId);
    }

    @Override
    public void update(UserTodo userTodo) {
        getSession().update(userTodo);
    }

    @Override
    public UserTodo getById(int userTodoId) {
        String hql = "from UserTodo where userTodoId=:userTodoId";
        return (UserTodo) getSession().createQuery(hql).setParameter("userTodoId", userTodoId).uniqueResult();
    }

    @Override
    public List<UserTodo> listByUser(int userId, int userTodoSetId) {
        String hql = "from UserTodo where userId=:userId and userTodoSetId=:userTodoSetId";
        return (List<UserTodo>) getSession().createQuery(hql).setParameter("userId", userId).setParameter(
                "userTodoSetId",
                userTodoSetId).list();
    }
}
