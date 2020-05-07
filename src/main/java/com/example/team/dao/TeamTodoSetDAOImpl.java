package com.example.team.dao;

import com.example.team.pojo.TeamTodoSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("teamTodoSetDAO")
@Transactional(rollbackFor = Exception.class)
public class TeamTodoSetDAOImpl implements TeamTodoSetDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(TeamTodoSet teamTodoSet) {
        getSession().save(teamTodoSet);
    }

    @Override
    public void delete(int teamTodoSetId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from TeamTodoSet where teamTodoSetId=:teamTodoSetId";
        TeamTodoSet teamTodoSet = (TeamTodoSet) session.createQuery(hql).setParameter("teamTodoSetId",teamTodoSetId).uniqueResult();
        session.delete(teamTodoSet);
        tx.commit();
        session.close();
    }

    @Override
    public void update(TeamTodoSet teamTodoSet) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(teamTodoSet);
        tx.commit();
        session.close();
    }

    @Override
    public TeamTodoSet getById(int teamTodoSetId) {
        String hql = "from TeamTodoSet where teamTodoSetId=:teamTodoSetId";
        return (TeamTodoSet) getSession().createQuery(hql).setParameter("teamTodoSetId",teamTodoSetId).uniqueResult();
    }

    @Override
    public TeamTodoSet getByName(String name) {
        String hql = "from TeamTodoSet where name=:name";
        return (TeamTodoSet) getSession().createQuery(hql).setParameter("name",name).uniqueResult();
    }

    @Override
    public List<TeamTodoSet> list(int teamId) {
        String hql = "from TeamTodoSet where teamId=:teamId";
        return (List<TeamTodoSet>) getSession().createQuery(hql).setParameter("teamId",teamId).list();
    }
}
