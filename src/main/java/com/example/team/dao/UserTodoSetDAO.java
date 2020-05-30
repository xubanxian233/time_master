package com.example.team.dao;

import com.example.team.pojo.UserTodoSet;

import java.util.List;

public interface UserTodoSetDAO {
    void add(UserTodoSet userTodoSet);

    void update(UserTodoSet userTodoSet);

    void delete(int userTodoSetId);

    UserTodoSet getById(int userTodoSetId);

    UserTodoSet getByName(String name);

    List<UserTodoSet> listById(int userId);
}
