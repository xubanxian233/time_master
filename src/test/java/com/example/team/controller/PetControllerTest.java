package com.example.team.controller;

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
import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetControllerTest {
    private MockMvc mvc;

    /*@Autowired
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
    public void getPet() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pet/getPet")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                //form表单格式传参
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .header("token","302210113bb9492088b4d908f4928049")
                .header("id","1");

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果



        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void updatePet() throws Exception {
        Map<String,String> name=new HashMap<>();
        name.put("name","hello");
        String s = JSON.toJSONString(name);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pet/updatePet")
                .contentType(MediaType.APPLICATION_JSON)
                //form表单格式传参
                .content(s)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .header("token","302210113bb9492088b4d908f4928049")
                .header("id","1");

        ResultActions result = mvc.perform(requestBuilder);

        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();// 返回执行请求的结果



        System.out.println("response------------------:"+mvcResult.getResponse().getContentAsString());
    }*/
}