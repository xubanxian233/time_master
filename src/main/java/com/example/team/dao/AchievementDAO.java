package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.Achievement;

import java.util.List;

public interface AchievementDAO {
    void add(Achievement achievement);
    void update(Achievement achievement);
    Achievement getAchievement(int userId,int aId);
    List<Achievement> getByUserId(int userId);
    List<Achievement> getUnAchievement(int userId);
}
