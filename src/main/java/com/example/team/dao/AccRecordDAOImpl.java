package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("accRecordDAO")
@Transactional(rollbackFor = Exception.class)
public class AccRecordDAOImpl extends BaseDAOImpl<AccRecord> implements AccRecordDAO {

    @Override
    public AccRecord getByUserId(int userId) {
        String hql = "from AccRecord where userId=:userId";
        return (AccRecord) getSession().createQuery(hql).setParameter("userId", userId).uniqueResult();
    }
}
