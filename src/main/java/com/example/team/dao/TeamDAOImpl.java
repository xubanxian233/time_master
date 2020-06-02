package com.example.team.dao;

import com.example.team.pojo.Team;
import com.example.team.pojo.TeamTodo;
import com.example.team.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository("teamDAO")
@Transactional(rollbackFor = Exception.class)
public class TeamDAOImpl implements TeamDAO {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void add(Team team) {
        getSession().save(team);
    }

    @Override
    public boolean update(Team team) {
        getSession().merge(team);
        return false;
    }

    @Override
    public boolean delete(int teamId) {
        Session session = getSession();
        Team team = session.get(Team.class, teamId);
        session.delete(team);
        return false;
    }

    @Override
    public Team getByTeamId(int teamId) {
        String hql = "from Team where teamId=:teamId";
        return (Team) getSession().createQuery(hql).setParameter("teamId", teamId).uniqueResult();
    }
}
