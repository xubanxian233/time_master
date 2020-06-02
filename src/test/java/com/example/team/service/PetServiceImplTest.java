package com.example.team.service;

import com.example.team.pojo.Pet;
import com.example.team.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class PetServiceImplTest {
    @Autowired
    private PetService petService;

    @Before
    public void avoidInterceptor(){

    }

    /*@Test
    void addPet() {
        Pet pet = new Pet();
        pet.setBirth(new Date(new java.util.Date().getTime()));
        pet.setSex(1);
        pet.setName("简时");
        pet.setPetStatusId(1);
        pet.setSkinId(1);
        pet.setLevel(1);
        pet.setUserId(5);
        petService.addPet(pet);
        Pet testPet = petService.getPetByUserId(5);
        Assert.assertEquals(pet.getPetId(),testPet.getPetId());
    }

    @Test
    void update() {
        Pet pet =petService.getPetByUserId(5);
        pet.setUserId(1);
        petService.update(pet);
        Pet testPet=petService.getPetById(pet.getPetId());
        Assert.assertEquals(1,testPet.getUserId());
    }

    @Test
    void getPetByUserId() {
        Pet pet=petService.getPetByUserId(1);
        Assert.assertEquals(1,pet.getUserId());
    }

    @Test
    void getPetById() {
        Pet pet=petService.getPetById(24);
        Assert.assertEquals(24,pet.getPetId());
    }*/
}