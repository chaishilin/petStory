package com.csl.petsStory.controller;

import com.alibaba.fastjson.JSON;
import com.csl.petsStory.entity.label.LabelEntity;
import com.csl.petsStory.entity.pet.PetAttribute;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/13 21:17
 * @Description:
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LabelControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    private String url = "http://localhost:8080/updateLabel";


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    void getLabel() {
    }


    @Test
    void updateLabel() throws Exception {
        setUp();
        LabelEntity entity = new LabelEntity();
        //entity.setLabelId("123456");
        entity.setAttribute(new PetAttribute());
        entity.setContent("测试标签 mvc mock");
        entity.setState("01");

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/updateLabel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JSON.toJSONString(entity)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        System.out.println("23");
        //assert response.getOutputStream("msg").equals("新增或修改成功！");

    }
}