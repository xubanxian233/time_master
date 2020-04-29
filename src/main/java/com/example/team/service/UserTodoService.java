package com.example.team.service;

import com.example.team.pojo.UserTodo;

import java.util.List;

public interface UserTodoService {
    List<UserTodo> listUserTodo(int userId);
    List<UserTodo> listUserTodo(int userTodoSetId,int userId);
}
