package com.example.team.dao;

import com.example.team.pojo.TeamTodo;

import java.util.List;

public interface TeamTodoDAO {
    void add(TeamTodo teamTodo);
    void delete(int teamTodoId);
    void update(TeamTodo teamTodo);
    void updateState(int teamTodoId,int todoStatusId);
    TeamTodo getById(int teamTodoId);
    TeamTodo getByName(String name);
    List<TeamTodo> list(int teamId);
    List<TeamTodo> list(int teamId,int teamTodoSetId);
    void updateSchedule();
}
