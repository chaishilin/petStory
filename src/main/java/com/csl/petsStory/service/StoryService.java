package com.csl.petsStory.service;

import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.entity.story.StoryEntity;

import java.util.List;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/8 21:46
 * @Description:
 */
public interface StoryService {
    String updateStory(StoryEntity entity);
    List<StoryEntity> getStory(StoryEntity entity);
    List<StoryEntity> getStoryItemFromRedis();
    StoryEntity randomSelectStoryItem(PetEntity entity);
    void hardDeleteStory();


}
