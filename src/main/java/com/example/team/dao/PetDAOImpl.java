package com.example.team.dao;

import com.example.team.pojo.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Repository("petDAO")
public class PetDAOImpl implements PetDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Pet pet) {
        getSession().save(pet);
        return pet.getPetId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(int petId) {
        getSession().delete(petId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Pet pet) {
        Session session=getSession();
        Transaction tx=session.beginTransaction();
        session.update(pet);
        tx.commit();
        session.close();
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
