package com.example.team.service;

import com.example.team.pojo.TeamTodo;

import java.util.List;

public interface TeamTodoService {
    boolean createTeamTodo(TeamTodo teamTodo);

    boolean updateTeamTodo(TeamTodo teamTodo);
    boolean updateState(int teamTodoId,int todoStatusId,int userId);
    void deleteTeamTodo(int teamTodoId);
    void deleteByUser(String name,int userId,int teamId);
    TeamTodo getById(int teamTodoId);
    TeamTodo getByUser(String name,int userId);
    List<TeamTodo> listByUser(int teamId,int userId);
    List<TeamTodo> listTeamTodo(int teamTodoSetId, int teamId);
    List<TeamTodo> listTeamTodo(int teamTodoSetId, int teamId,int userId);
    void updateSchedule();
}
