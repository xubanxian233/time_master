package com.example.team.dao;

import com.example.team.pojo.Team;
import com.example.team.pojo.User;

import java.util.Set;

public interface OrganizeDAO {
    Set<User> getMembers(int teamId);

    boolean inviteMember(int teamId, int userId);

    boolean deleteMember(int teamId, int userId);

    Set<Team> getTeams(int userId);

    boolean joinTeam(int teamId, int userId);

    boolean quitTeam(int teamId, int userId);
}
