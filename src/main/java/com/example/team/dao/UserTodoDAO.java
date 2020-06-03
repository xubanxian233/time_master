package com.example.team.dao;

import com.example.team.pojo.UserTodo;

import java.util.List;

public interface UserTodoDAO extends BaseDAO<UserTodo>{

    void updateSchedule();

    void updateState(int userTodoId, int todoStatusId);

    UserTodo getByName(String name);

    List<UserTodo> listByUser(int userId);

    List<UserTodo> listByUser(int userId, int userTodoSetId);
}
