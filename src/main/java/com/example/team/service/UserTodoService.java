package com.example.team.service;

import com.example.team.pojo.UserTodo;

import java.util.List;

public interface UserTodoService {
    boolean createUserTodo(UserTodo userTodo);

    boolean updateUserTodo(UserTodo userTodo);

    boolean updateState(int userTodoId, int todoStatusId);

    void deleteUserTodo(int userTodoId);

    UserTodo getById(int userTodoId);

    UserTodo getByName(String name,int userId);

    List<UserTodo> listUserTodo(int userId);

    List<UserTodo> listUserTodo(int userTodoSetId, int userId);

    void updateSchedule();
}
