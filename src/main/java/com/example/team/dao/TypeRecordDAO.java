package com.example.team.dao;

import com.example.team.pojo.TypeRecord;

public interface TypeRecordDAO {
    void add(TypeRecord typeRecord);
    void delete(int typeRecordId);
    void update(TypeRecord typeRecord);
    TypeRecord getById(int typeRecordId);
    TypeRecord getByUserId(int uid);
}
