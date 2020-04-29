package com.example.team.dao;

import com.example.team.pojo.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PetDAOImpl implements PetDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    public void add(Pet pet) {
        getSession().save(pet);
    }

    @Override
    public void delete(int petId) {
        getSession().delete(petId);
    }

    @Override
    public void update(Pet pet) {
        getSession().update(pet);
    }

    @Override
    public Pet getById(int petId) {
        String hql = "from Pet where petId=:petId";
        return (Pet) getSession().createQuery(hql).setParameter("petId", petId).uniqueResult();
    }

    @Override
    public Pet getByUserId(int userId) {
        String hql = "from Pet where userId=:userId";
        return (Pet) getSession().createQuery(hql).setParameter("userId", userId).uniqueResult();
    }
}
