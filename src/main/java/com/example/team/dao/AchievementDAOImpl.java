package com.example.team.dao;

import com.example.team.pojo.*;
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

@Repository("achievementDAO")
@Transactional(rollbackFor = Exception.class)
public class AchievementDAOImpl implements AchievementDAO {

    @PersistenceContext
    protected EntityManager entityManager;



    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void add(Achievement achievement) {
        getSession().save(achievement); }

    @Override
    public void update(Achievement achievement) {
        getSession().merge(achievement);
    }



    @Override
    public List<Achievement> getByUserId(int userId) {
        String hql="from Achievement where userId=:userId";
        return (List<Achievement>) getSession().createQuery(hql).setParameter("userId",userId).list();
    }

    @Override
    public Achievement getAchievement(int userId,int aId){
        String hql="from Achievement where userId=:userId and type=:aId";
        return (Achievement) getSession().createQuery(hql).setParameter("userId",userId).setParameter("aId",aId).uniqueResult();
    }
    @Override
    public List<Achievement> getUnAchievement(int userId){
        String hql="from Achievement where userId=:userId and status=:status";
        return (List<Achievement>) getSession().createQuery(hql).setParameter("userId",userId).setParameter("status","0").list();
    }
}
