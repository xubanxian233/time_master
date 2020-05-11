package com.example.team.controller;

import com.example.team.dao.DailyRecordDAO;
import com.example.team.dao.DailyRecordDAOImpl;
import com.example.team.dao.UserTodoDAO;
import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.MonthRecord;
import com.example.team.pojo.TypeRecord;
import com.example.team.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.sql.Date;

@Controller
@RequestMapping("/record")
public class RecordController extends BaseController {
    @Autowired
    private RecordService recordService;

    /**
     * getDailyRecord 获取当日使用记录
     *
     * @param date 当日日期
     * @return DailyRecord 当日使用记录
     */
    @RequestMapping("/getDailyRecord")
    @ResponseBody
    DailyRecord getDailyRecord(@RequestParam String date) {
        int userId = Integer.parseInt(request.getHeader("id"));
        Date dailyhDate = Date.valueOf(date);
        return recordService.getDailyRecord(userId, dailyhDate);
    }

    /**
     * getAccRecord 获取累计使用记录
     *
     * @param
     * @return AccRecord 累计使用记录
     */
    @RequestMapping("/getAccRecord")
    @ResponseBody
    AccRecord getAccRecord() {
        int userId = Integer.parseInt(request.getHeader("id"));
        AccRecord accRecord = recordService.getAccRecord(userId);
        return recordService.getAccRecord(userId);
    }

    /**
     * getMonthRecord 获取所选月份使用记录
     *
     * @param date 所选月份日期
     * @return MonthRecord 所选月份使用记录
     */
    @RequestMapping("/getMonthRecord")
    @ResponseBody
    MonthRecord getMonthRecord(@RequestParam String date) {
        int userId = Integer.parseInt(request.getHeader("id"));
        Date monthDate = Date.valueOf(date);
        return recordService.getMonthRecord(userId, monthDate);
    }

    /**
     * getTypeRecord 获取类型使用记录
     *
     * @param
     * @return List<TypeRecord> 用户类型使用记录
     */
    @RequestMapping("/getTypeRecord")
    @ResponseBody
    List<TypeRecord> getTypeRecord() {
        int userId = Integer.parseInt(request.getHeader("id"));
        return recordService.getTypeRecord(userId);
    }

    /**
     * getMonthRecordByMonth 获取所选月所有日使用记录
     *
     * @param date_1,date_2 所选开始月日期 所选开始月+1日期
     * @return List<DailyRecord> 月所有当日使用记录
     */
    @RequestMapping("/getDailyRecordByMonth")
    @ResponseBody
    List<DailyRecord> getDailyRecordByMonth(@RequestParam String date_1, @RequestParam String date_2) throws ParseException {
        int userId = Integer.parseInt(request.getHeader("id"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date litleMonthDate = Date.valueOf(date_1);
        Date bigMonthDate = Date.valueOf(date_2);
        return recordService.listDailyRecordByMonth(userId, litleMonthDate, bigMonthDate);
    }

    @RequestMapping("/setRecord")
    @ResponseBody
    public String setRecord(@RequestParam Map<String, Object> param) {
        String userId = request.getHeader("id"); //获取用户id
        String todoId = param.get("userTodoId").toString();//获取待办id
        String todoStatusId = param.get("statusId").toString();//获取待办状态
        String todosetId = param.get("todosetId").toString();//获取所属待办集，0为无属
        String todoTime = param.get("time").toString();//获取待办时间

        int tId=Integer.parseInt(todoId);
        int tTime = Integer.parseInt(todoTime);
        int tsId = Integer.parseInt(todoStatusId);
        int uId = Integer.parseInt(userId);

        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH);
        int d=cal.get(Calendar.DATE);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date time=null;
        try {
            time= (Date) sdf.parse(sdf.format(new Date(y,m,d)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (recordService.isExistDailyRecord(uId,time)) {
            recordService.updateRecordByUser(uId, tTime, tsId);
        } else {
            recordService.addRecordByUser(uId, tTime, tsId);
        }
        return "forword:/userTodo/update?userTodoId="+todoId+"&userTodoSetId="+todosetId+"&userId="+userId+"&todoStatusId="+tsId+"&name="+""+"&create="+""+"&typeId="+"";
    }
}
