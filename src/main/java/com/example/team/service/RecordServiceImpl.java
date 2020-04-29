package com.example.team.service;

import com.example.team.dao.*;
import com.example.team.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("recordService")
@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService {
    @Autowired
    private AccRecordDAO accRecordDAO;
    @Autowired
    private DailyRecordDAO dailyRecordDAO;
    @Autowired
    private MonthRecordDAO monthRecordDAO;
    @Autowired
    private TypeRecordDAO typeRecordDAO;
    @Autowired
    private TypeDAO typeDAO;
    @Override
    public void addRecordByUser(int userId) {
        AccRecord accRecord=new AccRecord();
        DailyRecord dailyRecord=new DailyRecord();
        MonthRecord monthRecord=new MonthRecord();
        List<Type> listType= typeDAO.listType();
        accRecord.setUserId(userId);
        accRecordDAO.add(accRecord);
        monthRecord.setUserId(userId);
        monthRecordDAO.add(monthRecord);
        for (Type t:listType){
            TypeRecord typeRecord= new TypeRecord();
            typeRecord.setUserId(userId);
            typeRecord.setTypeRecordId(t.getTypeId());
            typeRecordDAO.add(typeRecord);
        }
    }

    @Override
    public void updateRecordByUser(int userId) {

    }
}
