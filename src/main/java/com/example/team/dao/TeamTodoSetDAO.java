package com.example.team.dao;

import com.example.team.pojo.TeamTodoSet;

import java.util.List;

public interface TeamTodoSetDAO {
    void add(TeamTodoSet teamTodoSet);

    void delete(int teamTodoSetId);

    void update(TeamTodoSet teamTodoSet);

    TeamTodoSet getById(int teamTodoSetId);

    TeamTodoSet getByName(String name);

    List<TeamTodoSet> list(int teamId);
}
