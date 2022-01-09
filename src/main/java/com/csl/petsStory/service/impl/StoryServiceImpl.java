package com.csl.petsStory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csl.petsStory.entity.story.StoryEntity;
import com.csl.petsStory.mapper.StoryMapper;
import com.csl.petsStory.service.StoryService;
import com.csl.petsStory.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String updateStory(StoryEntity entity) {
        String id;
        //entity.setAttributeStr(entity.getAttributeStr());
        if(entity.getStoryId() == null || "".equals(entity.getStoryId())){
            id = numberGenerator.generateId("story");
            entity.setStoryId(id);
            mapper.insert(entity);
        }else{
            mapper.updateById(entity);
            id = entity.getStoryId();
        }

        return id;
    }

    @Override
    public List<StoryEntity> getStory(StoryEntity entity) {
        QueryWrapper<StoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("STATE","10");
        if(entity.getContent() != null && !"".equals(entity.getContent())){
            queryWrapper.like("CONTENT", entity.getContent());
        }
        return mapper.selectList(queryWrapper);
    }
}
