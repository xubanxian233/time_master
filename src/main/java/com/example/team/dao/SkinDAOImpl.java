package com.example.team.dao;

import com.example.team.pojo.Skin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository("skinDAO")
@Transactional(rollbackFor = Exception.class)
public class SkinDAOImpl implements SkinDAO {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }
    @Override
    public Skin getBySkinId(int skinId) {
        return getSession().get(Skin.class,skinId);
    }
}
