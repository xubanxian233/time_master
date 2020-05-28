package com.example.team.dao;

import com.example.team.pojo.Team;

public interface TeamDAO {
    void createTeam(Team team);
    void updateTeam(Team team);
    void deleteTeam(int teamId);
}
