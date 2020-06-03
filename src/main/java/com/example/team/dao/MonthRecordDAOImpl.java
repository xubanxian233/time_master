package com.example.team.dao;

import com.example.team.pojo.MonthRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Repository("monthRecordDAO")
@Transactional(rollbackFor = Exception.class)
public class MonthRecordDAOImpl extends BaseDAOImpl<MonthRecord> implements MonthRecordDAO {

    @Override
    public MonthRecord getByUserId(int userId, Date monthDate) {
        //Date date=new Date(monthDate.getTime()+24*60*60*1000);
        String hql = "from MonthRecord where userId=:userId and monthDate=:monthDate ";
        return (MonthRecord) getSession().createQuery(hql)
                .setParameter("userId", userId)
                .setParameter("monthDate", monthDate)
                .uniqueResult();
    }
}
