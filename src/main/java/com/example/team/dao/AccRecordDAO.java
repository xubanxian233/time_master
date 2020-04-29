package com.example.team.dao;

import com.example.team.pojo.AccRecord;

public interface AccRecordDAO {
    void add(AccRecord accRecord);
    void delete(int accRecordId);
    void update(AccRecord accRecord);
    AccRecord getById(int accRecordId);
    AccRecord getByUserId(int userId);
}
