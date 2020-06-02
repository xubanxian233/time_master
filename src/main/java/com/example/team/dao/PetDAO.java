package com.example.team.dao;

import com.example.team.pojo.Pet;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PetDAO {
    int add(Pet pet);

    void delete(int petId);

    void update(Pet pet);

    Pet getById(int petId);
  
    Pet getByUserId(int userId);
}
