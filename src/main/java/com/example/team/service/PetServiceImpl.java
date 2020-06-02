package com.example.team.service;

import com.example.team.dao.AccRecordDAO;
import com.example.team.dao.PetDAO;
import com.example.team.dao.UserDAO;
import com.example.team.pojo.AccRecord;
import com.example.team.pojo.Pet;
import com.example.team.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("petService")
@Transactional(rollbackFor = Exception.class)
public class PetServiceImpl implements PetService {
    @Autowired
    private PetDAO petDAO;
    @Autowired
    private AccRecordDAO accRecordDAO;
    @Autowired
    private UserDAO userDAO;


    @Override
    public void addPet(Pet pet) {
        petDAO.add(pet);
    }

    /**
     * update 修改宠物信息
     *
     * @param pet 修改的宠物
     * @return
     */
    @Override
    public void update(Pet pet) {
        petDAO.update(pet);
    }

    /**
     * getPetByUserId 获取pet通过用户
     *
     * @param userId 用户Id
     * @return 对应宠物
     */
    @Override
    public Pet getPetByUserId(int userId) {
        //return petDAO.getByUserId(userId);
        return userDAO.getById(userId).getPet();
    }

    /**
     * getPetById 获取宠物通过宠物id
     *
     * @param petId 宠物Id
     * @return 对应宠物
     */
    @Override
    public Pet getPetById(int petId) {
        return petDAO.getById(petId);
    }

    @Override
    public void updateLevel(int userId){
        //宠物等级
        Pet pet=getPetByUserId(userId);
        AccRecord accRecord1= accRecordDAO.getByUserId(userId);
        int level =accRecord1.getSuccessCount()/3;
        pet.setLevel(level);
        update(pet);
    }

}
