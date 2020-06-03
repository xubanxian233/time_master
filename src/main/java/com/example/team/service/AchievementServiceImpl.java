package com.example.team.service;

import com.example.team.dao.*;
import com.example.team.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("achievementService")
public class AchievementServiceImpl implements AchievementService {
    @Autowired
    private AchievementDAO achievementDAO;
    @Autowired
    private AchievementTypeDAO achievementTypeDAO;
    private AccRecordDAO accRecordDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAchievement(int userId) {
        Achievement achievement = null;
        List<AchievementType> aList=achievementTypeDAO.getAchievementType();
        for (AchievementType a:aList){
            achievement.setaId(userId);
            achievement.setStatus("0");
            achievement.setaId(a.getaId());
            achievementDAO.add(achievement);
        }
    }

    @Override
    public void updateAchievement(Achievement achievement){
        achievementDAO.update(achievement);
    }

    @Override
    public boolean isExistAchievement(int userId){
        if(achievementDAO.getByUserId(userId)!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Achievement> getByUserId(int userId) {
        return achievementDAO.getByUserId(userId);
    }

    @Override
    public List<Achievement> getUnAchievement(int userId){
        return achievementDAO.getUnAchievement(userId);
    }
}
