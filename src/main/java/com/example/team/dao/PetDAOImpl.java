package com.example.team.dao;


import com.example.team.pojo.Pet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("petDAO")
@Transactional(rollbackFor = Exception.class)
public class PetDAOImpl extends BaseDAOImpl<Pet> implements PetDAO {

}
