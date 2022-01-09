package com.csl.petsStory.service.impl;

import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.websocket.StoryWS;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/3 22:01
 * @Description:
 */
public class StoryRunable implements Runnable {
    private StoryWS storyWS;
    public StoryRunable(StoryWS storyWS){
        this.storyWS = storyWS;
    }

    @SneakyThrows
    @Override
    public void run() {
        storyWS.run();
    }
}