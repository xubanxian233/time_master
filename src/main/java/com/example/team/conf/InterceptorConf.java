package com.example.team.conf;

import com.example.team.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class InterceptorConf implements WebMvcConfigurer {
    @Bean
    LoginInterceptor myInterceptor() {
        return new LoginInterceptor();
    }
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器，拦截除/login以外的所有请求
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/sign")
                .excludePathPatterns("/user/findPassword")
                .excludePathPatterns("/user/gotoReset")
                .excludePathPatterns("/user/resetPassword");
    }
}
