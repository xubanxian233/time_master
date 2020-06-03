package com.example.team.dao;

import com.example.team.pojo.TeamTodo;

import java.util.List;

public interface TeamTodoDAO extends BaseDAO<TeamTodo>{
    void deleteByUser(String name,int userId);
    void updateState(int teamTodoId,int todoStatusId,int userId);
    TeamTodo getByUser(String name,int userId);
    List<TeamTodo> listByUser(int teamId,int userId);
    List<TeamTodo> list(int teamId,int teamTodoSetId);
    List<TeamTodo> list(int teamId,int teamTodoSetId,int userId);
    void updateSchedule();
}
