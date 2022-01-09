package com.csl.petsStory;

import com.csl.petsStory.websocket.StoryWS;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@MapperScan("com.csl.petsStory.mapper")
@SpringBootApplication
public class PetsStoryApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =  SpringApplication.run(PetsStoryApplication.class, args);
		//解决WebSocket不能注入的问题
		StoryWS.setApplicationContext(configurableApplicationContext);
	}

}
