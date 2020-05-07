package com.example.team.dao;

import com.example.team.pojo.TypeRecord;

import java.util.List;

public interface TypeRecordDAO {
    void add(TypeRecord typeRecord);
    void delete(int typeRecordId);
    void update(TypeRecord typeRecord);
    TypeRecord getById(int typeRecordId);
    List<TypeRecord> getByUserId(int uid);
}
