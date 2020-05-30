package com.example.team.service;

import com.example.team.pojo.Pet;
import com.example.team.pojo.Team;
import com.example.team.pojo.User;

import java.util.Set;

public interface UserService {
    public boolean verify(String userName, String password);

    public void quit(int userId);

    public boolean sign(User user, Pet pet);

    public int getUserId(String tel, String email, String name);

    public int getId();

    boolean updateUserInfo(int userId, String userName, String emain, String tel);

    public boolean updateUserPassword(int userId, String password);

    public User getById(int userId);

    Set<User> getMembers(int teamId);

    Set<Team> getTeams(int userId);

    boolean joinTeam(int teamId, int userId);

    boolean quitTeam(int teamId, int userId);
}
