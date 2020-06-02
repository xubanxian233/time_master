package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.Achievement;

import java.util.List;

public interface AchievementDAO {
    void add(Achievement achievement);
    Achievement getById(int achievementId);
    Achievement getByUserId(int userId);
    List<Achievement> getAchievement();
}
