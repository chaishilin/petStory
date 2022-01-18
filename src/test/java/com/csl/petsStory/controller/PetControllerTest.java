package com.csl.petsStory.controller;

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
 * @Date: 2022/1/18 9:56
 * @Description:
 */
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    private String url = "http://localhost:8080/newPet";

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    void newPet() throws Exception {
        setUp();
        String postBody = "{\"labels\":[],\"attribute\":{\"body\":{\"weight\":0,\"hanger\":0,\"friendly\":0,\"clean\":0},\"mod\":{\"happy\":0,\"angry\":0,\"terrified\":0},\"health\":{\"totalHealth\":0,\"headHealth\":0,\"bodyHealth\":0,\"footHealth\":0,\"tailHealth\":0},\"born\":{\"lucky\":0,\"intelligence\":0,\"strength\":0,\"charm\":0},\"ageAttr\":{\"age\":0}},\"userId\":\"2022011800038\"}";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/updateLabel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(postBody))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assert response.getStatus() == 200;

    }

}