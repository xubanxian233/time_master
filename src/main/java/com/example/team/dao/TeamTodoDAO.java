package com.example.team.dao;

import com.example.team.pojo.TeamTodo;

import java.util.List;

public interface TeamTodoDAO {
    void add(TeamTodo teamTodo);

    void delete(int teamTodoId);
    void deleteByUser(String name,int userId,int teamId);
    void update(TeamTodo teamTodo);
    void updateState(int teamTodoId,int todoStatusId,int userId);
    TeamTodo getById(int teamTodoId);
    TeamTodo getByUser(String name,int userId);
    List<TeamTodo> listByUser(int teamId,int userId);
    List<TeamTodo> list(int teamId,int teamTodoSetId);
    List<TeamTodo> list(int teamId,int teamTodoSetId,int userId);
    void updateSchedule();
}
