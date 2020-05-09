package com.example.team.util;

import com.example.team.pojo.User;
import com.example.team.pojo.UserTodo;
import com.example.team.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleUtil {
    @Autowired
    private UserTodoService userTodoService;
    @Scheduled(cron = "0 0 0 * * *")
    public void updateStatus(){
        userTodoService.upateSchedule();
    }
}
