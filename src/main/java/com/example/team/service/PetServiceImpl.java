package com.example.team.service;

import com.example.team.dao.PetDAO;
import com.example.team.dao.UserDAO;
import com.example.team.pojo.Pet;
import com.example.team.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("petService")
public class PetServiceImpl implements PetService {
    @Autowired
    private PetDAO petDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
}
