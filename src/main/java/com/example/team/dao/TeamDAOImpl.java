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
        session.merge(team);
        tx.commit();
        session.close();
        return false;
    }

    @Override
    public boolean delete(int teamId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        Team team =session.get(Team.class,teamId);
        session.delete(team);
        tx.commit();
        session.close();
        return false;
    }

    @Override
    public Team getByTeamId(int teamId) {
        String hql = "from Team where teamId=:teamId";
        return (Team) getSession().createQuery(hql).setParameter("teamId",teamId).uniqueResult();
    }
}
