package com.example.team.dao;

import com.example.team.pojo.AccRecord;
import com.example.team.pojo.DailyRecord;
import com.example.team.pojo.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import javax.persistence.PersistenceContext;


@Repository("petDAO")
@Transactional(rollbackFor = Exception.class)
public class PetDAOImpl implements PetDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public int add(Pet pet) {
        getSession().save(pet);
        return pet.getPetId();
    }

    @Override
    public void delete(int petId) {
        getSession().delete(petId);
    }

    @Override
    public void update(Pet pet) {
        getSession().merge(pet);
    }

    @Override
    public Pet getById(int petId) {
        String hql = "from Pet where petId=:petId";
        return (Pet) getSession().createQuery(hql).setParameter("petId", petId).uniqueResult();
    }

}
