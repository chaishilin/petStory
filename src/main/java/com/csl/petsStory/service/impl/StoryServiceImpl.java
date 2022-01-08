package com.csl.petsStory.service.impl;

import com.csl.petsStory.entity.story.StoryEntity;
import com.csl.petsStory.mapper.StoryMapper;
import com.csl.petsStory.service.StoryService;
import com.csl.petsStory.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/8 21:47
 * @Description:
 */
@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private NumberGenerator numberGenerator;
    @Autowired
    private StoryMapper mapper;
    @Override
    public String addStory(StoryEntity entity) {
        String id = numberGenerator.generateId("story");
        entity.setStoryId(id);
        mapper.insert(entity);
        return id;
    }
}
