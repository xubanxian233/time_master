package com.example.team.dao;

import com.example.team.pojo.TeamTodoSet;

import java.util.List;

public interface TeamTodoSetDAO extends BaseDAO<TeamTodoSet>{

    TeamTodoSet getByName(String name);

    List<TeamTodoSet> list(int teamId);
}
