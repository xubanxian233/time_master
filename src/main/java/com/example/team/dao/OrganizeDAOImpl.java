package com.example.team.dao;

import com.example.team.pojo.Team;
import com.example.team.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.Set;

@Repository("organizeDAO")
public class OrganizeDAOImpl implements OrganizeDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }
    @Override
    public Set<User> getMembers(int teamId) {
        String hql = "from Organize where teamId=:teamId";
        getSession().createQuery(hql).setParameter("teamId",teamId).list();
        return null;
    }

    @Override
    public boolean inviteMember(int teamId, int userId) {
        return false;
    }

    @Override
    public boolean deleteMember(int teamId, int userId) {
        return false;
    }

    @Override
    public Set<Team> getTeams(int userId) {
        return null;
    }

    @Override
    public boolean joinTeam(int teamId, int userId) {
        return false;
    }

    @Override
    public boolean quitTeam(int teamId, int userId) {
        return false;
    }
}
