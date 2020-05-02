package com.example.team.service;

import com.example.team.dao.UserTodoDAO;
import com.example.team.pojo.UserTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userTodoService")
@Transactional(rollbackFor = Exception.class)
public class UserTodoServiceImpl implements UserTodoService {
    @Autowired
    private UserTodoDAO userTodoDAO;

    @Override
    public void createUserTodo(UserTodo userTodo) {
        userTodoDAO.add(userTodo);
    }

    @Override
    public void updateUserTodo(UserTodo userTodo) {
        userTodoDAO.update(userTodo);
    }

    @Override
    public void deleteUserTodo(int userTodoId) {
        userTodoDAO.delete(userTodoId);
    }

    @Override
    public List<UserTodo> listUserTodo(int userId) {
        return userTodoDAO.listByUser(userId);
    }

    @Override
    public List<UserTodo> listUserTodo(int userTodoSetId, int userId) {
        return userTodoDAO.listByUser(userId,userTodoSetId);
    }
}
