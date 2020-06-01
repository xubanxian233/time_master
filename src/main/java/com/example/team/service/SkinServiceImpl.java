package com.example.team.service;

import com.example.team.dao.SkinDAO;
import com.example.team.pojo.Skin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
@Service("skinService")
public class SkinServiceImpl implements SkinService {
    @Autowired
    private SkinDAO skinDAO;
    @Override
    public Skin getSkin(int skinId) {
        return skinDAO.getBySkinId(skinId);
    }
}
