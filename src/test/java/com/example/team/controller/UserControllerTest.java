package com.example.team.controller;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;
    @Before
    public void setUp() throws Exception {
        //使用 WebApplicationContext 构建 MockMvc
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() throws Exception {
        Map<String,String> param=new HashMap<>();
        param.put("userName","wj@163.com");
        param.put("password","123456");
        String s = JSON.toJSONString(param);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                //form表单格式传参
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .content(s);

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果



        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void quit() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/quit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                //form表单格式传参
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .header("token","94ec6beb3a5548a2a79c786b2abab4ab")
                .header("id","1");

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果



        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void sign() throws Exception {
        Map<String,String> param=new HashMap<>();
        param.put("userName","123");
        param.put("tel","12345678911");
        param.put("email","123@qq.com");
        param.put("password","123456");
        param.put("sex","1");
        String s = JSON.toJSONString(param);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/sign")
                .contentType(MediaType.APPLICATION_JSON)
                //form表单格式传参
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .content(s);

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果



        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateInfo() throws Exception {
        Map<String,String> param=new HashMap<>();
        param.put("userName","admin2");
        param.put("tel","");
        param.put("email","");
        String s = JSON.toJSONString(param);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/updateInfo")
                .contentType(MediaType.APPLICATION_JSON)
                //form表单格式传参
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .header("token","94ec6beb3a5548a2a79c786b2abab4ab")
                .header("id","1")
                .content(s);

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果



        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updatePassword() throws Exception {
        Map<String,String> param=new HashMap<>();
        param.put("password","123456");
        String s = JSON.toJSONString(param);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/updatePassword")
                .contentType(MediaType.APPLICATION_JSON)
                //form表单格式传参
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .header("token","94ec6beb3a5548a2a79c786b2abab4ab")
                .header("id","1")
                .content(s);

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果



        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getUserInfo() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getUserInfo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                //form表单格式传参
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .header("token","94ec6beb3a5548a2a79c786b2abab4ab")
                .header("id","1");

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果



        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
    }
}