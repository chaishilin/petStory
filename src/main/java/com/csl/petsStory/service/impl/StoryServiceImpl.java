package com.csl.petsStory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.entity.story.StoryEntity;
import com.csl.petsStory.mapper.StoryMapper;
import com.csl.petsStory.service.StoryService;
import com.csl.petsStory.utils.NumberGenerator;
import com.csl.petsStory.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.stream.Collectors;

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
        if (entity.getStoryId() == null || "".equals(entity.getStoryId())) {
            id = numberGenerator.generateId("story");
            entity.setStoryId(id);
            mapper.insert(entity);
        } else {
            mapper.updateById(entity);
            id = entity.getStoryId();
        }

        return id;
    }

    @Override
    public List<StoryEntity> getStory(StoryEntity entity) {
        QueryWrapper<StoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("STATE", "10");
        if (entity.getContent() != null && !"".equals(entity.getContent())) {
            queryWrapper.like("CONTENT", entity.getContent());
        }
        return mapper.selectList(queryWrapper);
    }

    @Override
    public List<StoryEntity> getStoryItemFromRedis() {
        JedisCommands jedis = RedisUtil.getInstance();
        long storyLen = jedis.llen("story");
        if (storyLen == 0) {
            //把story加入redis
            getStory(new StoryEntity()).stream()
                    .map(item -> {
                        return jedis.lpush("story", JSONObject.toJSONString(item));
                    })
                    .collect(Collectors.toList());
            storyLen = jedis.llen("story");
        }
        List<String> storyStrs = jedis.lrange("story", 0, storyLen);
        return storyStrs.stream().map(item -> JSONObject.parseObject(item, StoryEntity.class)).collect(Collectors.toList());
    }

    @Override
    public StoryEntity randomSelectStoryItem(PetEntity entity) {
        List<StoryEntity> storyItems = getStoryItemFromRedis();
        List<StoryEntity> chooesAbleItems = storyItems.stream()
                .filter(item-> item.StoryItemSelectAble(entity))
                .collect(Collectors.toList());

        return chooesAbleItems.size() == 0? null:chooesAbleItems.get(0);
    }

}
