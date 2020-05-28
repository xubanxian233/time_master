package com.example.team.dao;

import com.example.team.pojo.Team;
import com.example.team.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    }

    @Override
    public boolean update(Team team) {
        return false;
    }

    @Override
    public boolean delete(int teamId) {
        return false;
    }

    @Override
    public Set<User> getMembers() {
        return null;
    }

    @Override
    public boolean addMember(int teamId, int userId) {
        return false;
    }

    @Override
    public boolean inviteMember(int teamId, int userId) {
        return false;
    }

    @Override
    public boolean deleteMember(int teamId, int userId) {
        return false;
    }
}
