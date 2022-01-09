package com.csl.petsStory;

import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.service.StoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetsStoryApplicationTests {
	@Autowired
	private PetEntity entity;
	@Autowired
	private StoryService storyService;


	@Test
	void contextLoads()  {
		entity.getPetAttribute().getMod().setAngry(6);
		Object resulot = storyService.randomSelectStoryItem(entity);
		System.out.println(resulot);
		System.out.println("-------");
		entity.getPetAttribute().getMod().setAngry(9);
		resulot = storyService.randomSelectStoryItem(entity);
		System.out.println(resulot);
	}

}
