package com.example.team.service;

import com.example.team.dao.*;
import com.example.team.pojo.AccRecord;
import com.example.team.pojo.Achievement;
import com.example.team.pojo.Pet;
import com.example.team.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("achievementService")
public class AchievementServiceImpl implements AchievementService {
    @Autowired
    private AchievementDAO achievementDAO;
    private AccRecordDAO accRecordDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAchieevement(Achievement achievement) {
        achievementDAO.add(achievement);
    }

    @Override
    public Achievement getByUserId(int userId) {
        return achievementDAO.getByUserId(userId);
    }



}
