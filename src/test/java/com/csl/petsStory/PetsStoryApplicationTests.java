package com.csl.petsStory;

import com.alibaba.fastjson.JSONObject;
import com.csl.petsStory.entity.label.LabelEntity;
import com.csl.petsStory.entity.pet.PetAttribute;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetsStoryApplicationTests {

	@Resource
	private RestTemplate restTemplate;


	private String url = "http://localhost:8080/updateLabel";

	@Test
	void contextLoads()  {
		LabelEntity entity = new LabelEntity();
		entity.setLabelId("123456");
		entity.setAttribute(new PetAttribute());
		entity.setContent("测试标签");
		entity.setState("01");
		ResponseEntity<JSONObject> result = restTemplate.postForEntity(url,entity, JSONObject.class);
		System.out.println(result);
	}

}
