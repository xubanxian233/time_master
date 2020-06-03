package com.example.team.controller;


import com.example.team.pojo.Achievement;
import com.example.team.pojo.AchievementType;
import com.example.team.service.AchievementService;
import com.example.team.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/achievement")
public class AchievementController extends BaseController{
    @Autowired
    private AchievementService achievementService;


    //    宠物成就:
// 1.坚持不懈：每日完成一项待办（>=21天）
// 2.屡败屡战：某项待办连续五天都是失败
// 3.我一定会回来的：获得屡败屡战后该代办成功完成
// 系列成就1:
//    5.初出茅庐：总计完成时间>10分钟
//    6.小试牛刀：总计完成时间>100分钟
//    7.持之以恒 ：总计完成时间>500分钟
//    8.毅力惊人：总计完成时间>1000分钟
//    9.时间管理大师：总计完成时间>5000分钟
    /**
     * getAchievement 获取当日使用记录
     *
     * @return DailyRecord 当日使用记录
     */
    @RequestMapping(value = "/getAchievement", method = RequestMethod.POST)
    @ResponseBody
    List<Achievement> getAchievement(@RequestParam int userId){
       return achievementService.getByUserId(userId);
    }
    private void AchievementOne(int userId){

    }
}
