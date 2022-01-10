package com.csl.petsStory;

import com.csl.petsStory.schedule.ScheduleTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetsStoryApplicationTests {
	@Autowired
	private ScheduleTask scheduleTask;


	@Test
	void contextLoads()  {
		scheduleTask.recordUserCount();
	}

}
