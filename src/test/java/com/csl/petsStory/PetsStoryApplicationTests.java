package com.csl.petsStory;

import com.alibaba.fastjson.JSONObject;
import com.csl.petsStory.entity.pet.BodyAttribute;
import com.csl.petsStory.entity.pet.BornAttribute;
import com.csl.petsStory.entity.pet.HealthAttribute;
import com.csl.petsStory.entity.pet.ModAttribute;
import com.csl.petsStory.entity.pet.PetAttribute;
import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.entity.story.StoryEntity;
import com.csl.petsStory.mapper.StoryMapper;
import lombok.Data;
import lombok.experimental.Helper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetsStoryApplicationTests {
	@Autowired
	private PetEntity entity;
	@Autowired
	private StoryMapper mapper;


	@Test
	void contextLoads() throws IllegalAccessException {
		PetAttribute petAttribute = entity.getPetAttribute();
		petAttribute.getBody().setClean(2);
		petAttribute.getBorn().setIntelligence(5);
		StoryEntity storyEntity = mapper.selectById("2022010800001");

		System.out.println(JSONObject.toJSONString(entity));
		System.out.println(JSONObject.toJSONString(entity));
		//{"age":0,"maxAge":180,"petAttribute":{"body":{"clean":2,"friendly":0,"hanger":0,"size":0,"weight":0},"born":{"charm":0,"intelligence":5,"lucky":0,"strength":0},"health":{"bodyHealth":0,"footHealth":0,"headHealth":0,"tailHealth":0,"totalHealth":0},"mod":{"angry":5,"defaultValue":5,"happy":5,"sad":5,"terrified":5}}}

	}

}
