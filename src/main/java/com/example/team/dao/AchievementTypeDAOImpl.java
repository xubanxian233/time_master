package com.example.team.dao;

import com.example.team.pojo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("achievementTypeDAO")
public class AchievementTypeDAOImpl implements AchievementTypeDAO {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }


    @Override
    public AchievementType getById(int achievementId) {
        String hql="from AchievementType where aId=:achievementId";
        return (AchievementType) getSession().createQuery(hql).setParameter("achievementId",achievementId).uniqueResult();
    }

    @Override
    public List<AchievementType> getAchievementType(){
        String hql = "from AchievementType";
        return (List<AchievementType>) getSession().createQuery(hql).list();
    }
}
