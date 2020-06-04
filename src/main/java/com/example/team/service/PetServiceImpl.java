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
    /**
     * @description: 添加宠物
     * @Param: [pet]
     * @return: void
     * @update: time: 2020/6/3 9:33
     */
    public void addPet(Pet pet) {
        petDAO.add(pet);
    }

    @Override
    /**
     * @description: 更细宠物
     * @Param: [pet]
     * @return: void
     * @update: time: 2020/6/3 9:34
     */
    public void update(Pet pet) {
        petDAO.update(pet);
    }

    @Override
    /**
     * @description: 获取宠物
     * @Param: [userId]
     * @return: com.example.team.pojo.Pet
     * @update: time: 2020/6/3 9:34
     */
    public Pet getPetByUserId(int userId) {
        return userDAO.get(User.class, userId).getPet();
    }

    @Override
    /**
     * @description: 获取宠物
     * @Param: [petId]
     * @return: com.example.team.pojo.Pet
     * @update: time: 2020/6/3 9:34
     */
    public Pet getPetById(int petId) {
        return petDAO.get(Pet.class, petId);
    }

    @Override
    /**
     * @description: 更新宠物等级
     * @Param: [userId]
     * @return: void
     * @update: time: 2020/6/3 9:34
     */
    public void updateLevel(int userId) {
        //宠物等级
        Pet pet = userDAO.get(User.class, userId).getPet();
        AccRecord accRecord = accRecordDAO.getByUserId(userId);
        int level = pet.getLevel();
        int total = accRecord.getSuccessCount() - accRecord.getFailCount();
        int upTotal = (level * level + level) / 2;
        int downTotal = ((level - 1) * (level - 1) + level - 1) / 2;
        if (total == upTotal) {
            if (level < 60) {
                pet.setLevel(level + 1);
            }
        } else if (total == downTotal) {
            if (level > 1) {
                pet.setLevel(level - 1);
            }
        }
        petDAO.update(pet);
    }

}
