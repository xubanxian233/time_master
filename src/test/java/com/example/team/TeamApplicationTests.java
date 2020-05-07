package com.example.team;

import com.alibaba.fastjson.JSONObject;
import com.example.team.service.RedisService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TeamApplicationTests {

    private JSONObject json = new JSONObject();

    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() throws Exception {
    }


    /**
     * 插入字符串
     */
    @Test
    public void setString() {
        redisService.set("redis_string_test", "springboot redis test");
    }

    /**
     * 获取字符串
     */
    @Test
    public void getString() {

    }

    /**
     * 插入对象
     */
    @Test
    public void setObject() {

    }

    /**
     * 获取对象
     */
    @Test
    public void getObject() {

    }

    /**
     * 插入对象List
     */
    @Test
    public void setList() {

    }

    /**
     * 获取list
     */
    @Test
    public void getList() {

    }


}

class Person {
    private String name;
    private String sex;

    public Person() {

    }

    public Person(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
