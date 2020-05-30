package com.example.team.util;

import com.example.team.service.TeamTodoService;
import com.example.team.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduleUtil {
    @Autowired
    private UserTodoService userTodoService;

    @Autowired
    private TeamTodoService teamTodoService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateStatus() {
        userTodoService.updateSchedule();
        teamTodoService.updateSchedule();
    }
}
