package com.example.team.service;

import com.example.team.pojo.Pet;

public interface PetService {
    void addPet(Pet pet);

    void update(Pet pet);

    Pet getPetByUserId(int userId);

    Pet getPetById(int petId);
    void updateLevel(int userId);
}
