package com.example.team.dao;

import com.example.team.pojo.TeamTodo;

import java.util.List;

public interface TeamTodoDAO {
    void add(TeamTodo teamTodo);
    void delete(int teamTodoId);
    void update(TeamTodo teamTodo);
    TeamTodo getById(int teamTodoId);
    List<TeamTodo> list(int teamId);
}
