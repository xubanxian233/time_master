package com.example.team.service;

import com.example.team.pojo.TeamTodo;

import java.util.List;

public interface TeamTodoService {
    boolean createTeamTodo(TeamTodo teamTodo);
    boolean updateTeamTodo(TeamTodo teamTodo);
    void deleteTeamTodo(int teamTodoId);
    TeamTodo getById(int teamTodoId);
    List<TeamTodo> listTeamTodo(int teamId);
    List<TeamTodo> listTeamTodo(int teamTodoSetId, int teamId);
}
