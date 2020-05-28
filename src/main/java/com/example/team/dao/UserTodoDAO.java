package com.example.team.dao;

import com.example.team.pojo.UserTodo;

import java.util.List;

public interface UserTodoDAO {
    void add(UserTodo userTodo);
    void delete(int userTodoId);
    void update(UserTodo userTodo);
    void updateSchedule();
    UserTodo getById(int userTodoId);
    UserTodo getByName(String name);
    List<UserTodo> listByUser(int userId);
    List<UserTodo> listByUser(int userId, int userTodoSetId);
}
