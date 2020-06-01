package com.example.team.dao;

import com.example.team.pojo.Skin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository("skinDAO")
public class SkinDAOImpl implements SkinDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }
    @Override
    public Skin getBySkinId(int skinId) {
        return getSession().get(Skin.class,skinId);
    }
}
