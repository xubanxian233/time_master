package com.example.team.service;

import com.example.team.dao.UserDAO;
import com.example.team.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private String token;
    private int id;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RedisService redisService;

    public boolean verify(String userName, String password) {
        User user = userDAO.getByEmail(userName);
        if (user != null && password.equals(user.getPassword())) {
            token = UUID.randomUUID().toString().replaceAll("-", "");
            id = user.getUserId();
            redisService.set(String.valueOf(id), token);
            return true;
        }
        return false;
    }

    public boolean sign(User user) {
        String email = user.getEmail();
        String tel = user.getTel();
        String name = user.getName();
        User user_1 = userDAO.getByEmail(email);
        User user_2 = userDAO.getByTel(tel);
        User user_3 = userDAO.getByName(name);
        if (user_1 == null && user_2 == null && user_3 == null) {
            userDAO.add(user);
            return true;
        }
        return false;
    }

    public int getUserId(String tel, String email, String name) {
        if (tel != null)
            return userDAO.getByTel(tel).getUserId();
        if (email != null)
            return userDAO.getByEmail(email).getUserId();
        if (name != null)
            return userDAO.getByName(name).getUserId();
        return 0;
    }

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }
}
