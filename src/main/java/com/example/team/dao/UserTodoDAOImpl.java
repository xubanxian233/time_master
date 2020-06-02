package com.example.team.dao;

import com.example.team.pojo.UserTodo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "userTodoDAO")
@Transactional(rollbackFor = Exception.class)
public class UserTodoDAOImpl implements UserTodoDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void add(UserTodo userTodo) {
        getSession().save(userTodo);
    }

    @Override
    public void delete(int userTodoId) {
        Session session = getSession();
        String hql = "from UserTodo where userTodoId=:userTodoId";
        UserTodo userTodo = (UserTodo) session.createQuery(hql).setParameter("userTodoId", userTodoId).uniqueResult();
        session.delete(userTodo);
    }

    @Override
    public void update(UserTodo userTodo) {
        getSession().update(userTodo);
    }

    @Override
    public void updateSchedule() {
        Session session = getSession();
        String hqlUpdate = "update UserTodo as obj set todoStatusId = :status where todoStatusId != :oldStatus";
        int updatedEntities = session.createQuery(hqlUpdate)
                .setParameter("status", 1)
                .setParameter("oldStatus", 1)
                .executeUpdate();
    }

    @Override
    public void updateState(int userTodoId, int todoStatusId) {
        Session session = getSession();
        String hqlUpdate = "update UserTodo as obj set todoStatusId = :status where userTodoId = :userTodoId";
        int updatedEntities = session.createQuery(hqlUpdate)
                .setParameter("status", todoStatusId)
                .setParameter("userTodoId", userTodoId)
                .executeUpdate();
    }

    @Override
    public UserTodo getById(int userTodoId) {
        String hql = "from UserTodo where userTodoId=:userTodoId";
        return (UserTodo) getSession().createQuery(hql).setParameter("userTodoId", userTodoId).uniqueResult();
    }

    @Override
    public UserTodo getByName(String name) {
        String hql = "from UserTodo where name=:name";
        return (UserTodo) getSession().createQuery(hql).setParameter("name", name).uniqueResult();
    }

    @Override
    public List<UserTodo> listByUser(int userId) {
        String hql = "from UserTodo where userId=:userId";
        return (List<UserTodo>) getSession().createQuery(hql).setParameter("userId", userId).list();
    }

    @Override
    public List<UserTodo> listByUser(int userId, int userTodoSetId) {
        String hql = "from UserTodo where userId=:userId and userTodoSetId=:userTodoSetId";
        return (List<UserTodo>) getSession().createQuery(hql).setParameter("userId", userId).setParameter("userTodoSetId", userTodoSetId).list();
    }
}
