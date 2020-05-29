package com.example.team.dao;

import com.example.team.pojo.Team;
import com.example.team.pojo.TeamTodo;
import com.example.team.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.Set;

@Repository("teamDAO")
public class TeamDAOImpl implements TeamDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(Team team) {
        getSession().save(team);
    }

    @Override
    public boolean update(Team team) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(team);
        tx.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(int teamId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from TeamTodo where teamId=:teamId";
        TeamTodo teamTodo = (TeamTodo) session.createQuery(hql).setParameter("teamId",teamId).uniqueResult();
        session.delete(teamTodo);
        tx.commit();
        session.close();
        return false;
    }
}
