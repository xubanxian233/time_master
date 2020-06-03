package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.Achievement;
import com.example.team.pojo.AchievementType;

import java.util.List;

public interface AchievementTypeDAO extends BaseDAO<AchievementType> {
    int getAchievementId(int acctime,int userId);
    List<AchievementType> getAchievementType();
}
