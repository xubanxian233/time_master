package com.example.team;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManagerFactory;

@Controller
@SpringBootApplication
@EnableScheduling
public class IndexControler {
    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "helloword!";
    }

    public static void main(String[] args) {
        SpringApplication.run(IndexControler.class, args);
    }


}
