package com.example.team.dao;

import com.example.team.pojo.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository("achievementTypeDAO")
@Transactional(rollbackFor = Exception.class)
public class AchievementTypeDAOImpl extends BaseDAOImpl<AchievementType> implements AchievementTypeDAO {

    @Override
    public int getAchievementId(int acctime, int userId) {
        String hql="select max(obj.aId) from AchievementType as obj where obj.acctime<=:acctime";
        return (int) getSession().createQuery(hql).setParameter("acctime",acctime).uniqueResult();
    }

    @Override
    public List<AchievementType> getAchievementType(){
        String hql = "from AchievementType";
        return (List<AchievementType>) getSession().createQuery(hql).list();
    }
}
