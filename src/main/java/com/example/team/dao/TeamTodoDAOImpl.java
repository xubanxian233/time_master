package com.example.team.dao;

import com.example.team.pojo.Team;
import com.example.team.pojo.TeamTodo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("teamTodoDAO")
@Transactional(rollbackFor = Exception.class)
public class TeamTodoDAOImpl implements TeamTodoDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    /*@Override
    public void add(TeamTodo teamTodo) {
        getSession().save(teamTodo);
    }

    @Override
    public void delete(int teamTodoId) {
        getSession().delete(teamTodoId);
    }

    @Override
    public void update(TeamTodo teamTodo) {
        getSession().update(teamTodo);
    }

    @Override
    public TeamTodo getById(int teamTodoId) {
        String hql = "from TeamTodo where teamTodoId=:teamTodoId";
        return (TeamTodo) getSession().createQuery(hql).setParameter("teamTodoId",teamTodoId).uniqueResult();
    }

    @Override
    public List<TeamTodo> list(int teamTodoId) {
        String hql = "from TeamTodo where teamTodoId=:teamTodoId";
        return (List<TeamTodo>) getSession().createQuery(hql).setParameter("teamTodoId",teamTodoId).list();
    }*/
}
