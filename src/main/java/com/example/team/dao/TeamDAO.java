package com.example.team.dao;

import com.example.team.pojo.Team;
import com.example.team.pojo.User;

import java.util.List;
import java.util.Set;

public interface TeamDAO {
    void add(Team team);

    boolean update(Team team);

    boolean delete(int teamId);

    Set<User> getMembers();

    boolean addMember(int teamId, int userId);

    boolean inviteMember(int teamId, int userId);

    boolean deleteMember(int teamId, int userId);
}
