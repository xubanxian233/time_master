package com.example.team.dao;

import com.example.team.pojo.Pet;

public interface PetDAO {
    int add(Pet pet);

    void delete(int petId);

    void update(Pet pet);

    Pet getById(int petId);

}
