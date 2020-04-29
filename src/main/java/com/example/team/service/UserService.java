package com.example.team.service;

import com.example.team.pojo.User;

public interface UserService {
    public boolean verify(String userName,String password);
    public boolean sign(User user);
    public int getUserId(String tel,String email,String name);
    public String getToken();
    public int getId();
}
