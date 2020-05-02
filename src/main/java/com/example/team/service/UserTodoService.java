package com.example.team.service;

import com.example.team.pojo.UserTodo;

import java.util.List;

public interface UserTodoService {
    void createUserTodo(UserTodo userTodo);
    void updateUserTodo(UserTodo userTodo);
    void deleteUserTodo(int userTodoId);
    List<UserTodo> listUserTodo(int userId);
    List<UserTodo> listUserTodo(int userTodoSetId, int userId);
}
