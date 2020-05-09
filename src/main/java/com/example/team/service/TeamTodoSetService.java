package com.example.team.service;

import com.example.team.pojo.TeamTodo;
import com.example.team.pojo.TeamTodoSet;

import java.util.List;

public interface TeamTodoSetService {
    boolean createTeamTodoSet(TeamTodoSet teamTodoSet);
    boolean updateTeamTodoSet(TeamTodoSet teamTodoSet);
    void deleteTeamTodoSet(int teamTodoSetId);
    TeamTodoSet getById(int teamTodoSetId);
    List<TeamTodoSet> listByTeamId(int teamId);
}
