package com.example.team.service;

import com.example.team.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "redisService")
@Transactional(rollbackFor = Exception.class)
public class RedisServiceImpl<T> implements RedisService<T> {
    @Autowired
    private RedisUtil redisUtil;

    public void set(String key, String value){
        if(redisUtil.set(key,value))
            System.out.println("success");
        else
        System.out.println("fail");
    }

    public String get(String key){
        Object o=redisUtil.get(key);
            System.out.println(o);
            return (String)o;
    }

    @Override
    public void setExpire(String key, long expire) {
        redisUtil.expire(key,expire);
    }

    @Override
    public void delete(String key) {
        redisUtil.del(key);
    }

}
