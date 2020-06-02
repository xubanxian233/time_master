package com.example.team;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


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

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        /** 设置视图路径的前缀 */
        resolver.setPrefix("/WEB-INF/jsp/");
        /** 设置视图路径的后缀 */
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
