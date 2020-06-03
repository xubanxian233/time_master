package com.example.team.controller;


import com.example.team.pojo.AccRecord;
import com.example.team.pojo.Achievement;
import com.example.team.pojo.AchievementType;
import com.example.team.pojo.DailyRecord;
import com.example.team.service.AchievementService;
import com.example.team.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/achievement")
public class AchievementController extends BaseController{
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private RecordService recordService;

    private Date nTime;
    private Date bTime;
    private Achievement achievement;

    /**
     * getAchievement 获取用户成就并判断成就是否达成
     *
     * @param userId 用户id
     * @return List<Achievement> 用户的成就列表
     */
    @RequestMapping(value = "/getAchievement", method = RequestMethod.POST)
    @ResponseBody
    List<Achievement> getAchievement(@RequestParam int userId){
        //判断是否存在记录
        if (achievementService.isExistAchievement(userId)){
            achievementService.addAchievement(userId);
        }
        //获取未达成的成就列表
        List<Achievement> aList=achievementService.getUnAchievement(userId);
        //判断是否达成
        for (Achievement a:aList){
            achievement=a;
            switch (a.getaId()){
                case 1:AchievementOne(userId);
                    break;
                case 2:AchievementTwo(userId);
                    break;
                case 3:AchievementThree(userId);
                    break;
                case 4:AchievementFour(userId);
                    break;
                case 5:AchievementFive(userId);
                    break;
                case 6:AchievementSix(userId);
                    break;
            }

        }
        return achievementService.getByUserId(userId);
    }

    //获取前一天的日期
    private void getBeforeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.DAY_OF_MONTH, -1);
        java.util.Date d = calendar.getTime();
        bTime = new Date(d.getTime());
    }

    //获取当前年月日
    public void getCurrentTime() {
        java.util.Date date = new java.util.Date();
        nTime = new Date(date.getTime());
    }

    //    宠物成就:
// 1.坚持不懈：每日完成一项待办（>=21天）
// 系列成就1:
//    2.初出茅庐：总计完成时间>10分钟
//    3.小试牛刀：总计完成时间>100分钟
//    4.持之以恒 ：总计完成时间>500分钟
//    5.毅力惊人：总计完成时间>1000分钟
//    6.时间使者：总计完成时间>5000分钟
    private void AchievementOne(int userId){
        int i=0;
        getCurrentTime();
        bTime=nTime;
        while(recordService.getDailyRecord(userId,nTime)!=null){
            DailyRecord dailyRecord=recordService.getDailyRecord(userId,nTime);
            if(dailyRecord.getSuccessCount()>0){
                i++;
            }
            getBeforeDate(bTime);
            if (i>21){
                achievement.setStatus("1");
                achievementService.updateAchievement(achievement);
                break;
            }
        }
    }

    private void AchievementTwo(int userId) {
        AccRecord accRecord=recordService.getAccRecord(userId);
        if(accRecord!=null){
            if (accRecord.getAcctime()>10){
                achievement.setStatus("1");
                achievementService.updateAchievement(achievement);
            }

        }
    }

    private void AchievementThree(int userId) {
        AccRecord accRecord=recordService.getAccRecord(userId);
        if(accRecord!=null){
            if (accRecord.getAcctime()>100){
                achievement.setStatus("1");
                achievementService.updateAchievement(achievement);
            }

        }
    }

    private void AchievementFour(int userId) {
        AccRecord accRecord=recordService.getAccRecord(userId);
        if(accRecord!=null){
            if (accRecord.getAcctime()>500){
                achievement.setStatus("1");
                achievementService.updateAchievement(achievement);
            }

        }
    }

    private void AchievementFive(int userId) {
        AccRecord accRecord=recordService.getAccRecord(userId);
        if(accRecord!=null){
            if (accRecord.getAcctime()>1000){
                achievement.setStatus("1");
                achievementService.updateAchievement(achievement);
            }

        }
    }

    private void AchievementSix(int userId) {
        AccRecord accRecord=recordService.getAccRecord(userId);
        if(accRecord!=null){
            if (accRecord.getAcctime()>5000){
                achievement.setStatus("1");
                achievementService.updateAchievement(achievement);
            }

        }
    }


}
