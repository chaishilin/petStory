package com.csl.petsStory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@SpringBootApplication
public class PetsStoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsStoryApplication.class, args);
	}

}
