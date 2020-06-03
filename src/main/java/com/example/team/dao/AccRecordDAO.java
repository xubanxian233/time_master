package com.example.team.dao;

import com.example.team.pojo.AccRecord;

public interface AccRecordDAO extends BaseDAO<AccRecord> {

    AccRecord getByUserId(int userId);
}
