package com.example.team.service;

import com.example.team.pojo.Achievement;
import com.example.team.pojo.Pet;

public interface AchievementService{
    void addAchieevement(Achievement achievement);
    Achievement getByUserId(int userId);
}
