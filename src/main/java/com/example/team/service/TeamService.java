package com.example.team.service;

import com.example.team.pojo.Team;
import com.example.team.pojo.User;
import org.springframework.stereotype.Service;


public interface TeamService {
    void createTeam(User user, Team team);

    void updateTeam(Team team);

    void deleteTeam(int teamId);
}
