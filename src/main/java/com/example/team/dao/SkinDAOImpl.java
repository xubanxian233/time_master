package com.example.team.dao;

import com.example.team.pojo.Skin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("skinDAO")
@Transactional(rollbackFor = Exception.class)
public class SkinDAOImpl extends BaseDAOImpl<Skin> implements SkinDAO {
}
