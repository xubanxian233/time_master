package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.Achievement;
import com.example.team.pojo.AchievementType;

import java.util.List;

public interface AchievementTypeDAO {
    AchievementType getById(int achievementId);
    List<AchievementType> getAchievementType();
}
