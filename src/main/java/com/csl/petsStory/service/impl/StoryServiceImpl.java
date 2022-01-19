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
        entity.setAttributeStr(entity.getAttributeStr());
        if (entity.getStoryId() == null || "".equals(entity.getStoryId())) {
            id = numberGenerator.generateId("storyId");
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
        if (entity.getState() != null && !"".equals(entity.getState())) {
            queryWrapper.eq("STATE", entity.getState());
        }
        if (entity.getTag() != null && !"".equals(entity.getTag())) {
            queryWrapper.eq("TAG", entity.getTag());
        }
        return mapper.selectList(queryWrapper);
    }

    @Override
    public List<StoryEntity> getStoryItemFromRedis() {
        JedisCommands jedis = RedisUtil.getInstance();
        long storyLen = jedis.llen("storyList");
        if (storyLen == 0) {
            StoryEntity queryEntity = new StoryEntity();
            queryEntity.setState("01");
            //把story加入redis
            getStory(queryEntity).stream()
                    .map(item -> {
                        return jedis.lpush("storyList", JSONObject.toJSONString(item));
                    })
                    .collect(Collectors.toList());
            storyLen = jedis.llen("storyList");
        }
        List<String> storyStrs = jedis.lrange("storyList", 0, storyLen);
        return storyStrs.stream().map(item -> JSONObject.parseObject(item, StoryEntity.class)).collect(Collectors.toList());
    }

    @Override
    public StoryEntity randomSelectStoryItem(PetEntity petEntity) {
        List<StoryEntity> storyItems = getStoryItemFromRedis();
        String tag = petEntity.getPetAttribute().getAgeAttr().reachLimit() ? "死亡" : "日常";
        List<StoryEntity> chooesAbleItems = storyItems.stream()
                .filter(item -> item.getTag().equals(tag))
                .filter(item -> item.StoryItemSelectAble(petEntity))
                .sorted((item1, item2) -> compareAttr(petEntity,item1,item2))
                .limit(5)
                .collect(Collectors.toList());

        int index = (int) (Math.random() * (chooesAbleItems.size()));
        return chooesAbleItems.size() == 0 ? null : chooesAbleItems.get(index);
    }

    private int compareAttr(PetEntity petEntity, StoryEntity item1, StoryEntity item2) {
        double b1 = calTotalRange(item1);
        double b2 = calTotalRange(item2);
        if (b1 == b2) {
            return 0;
        } else {
            return b1 > b2 ? 1 : -1;
        }
    }

    /**
     * 对于满足要求的故事，按照难满足程度排序（上下限之间的范围表明了是否难以满足）
     * @param storyEntity
     * @return
     */
    public int calTotalRange(StoryEntity storyEntity){
        int result = 0;
        List<Integer> lowArray = storyEntity.getAttributeLow().toArray();
        List<Integer> highArray = storyEntity.getAttributeHigh().toArray();
        for(int i = 0 ; i < lowArray.size();i++){
            result += highArray.get(i)- lowArray.get(i);
        }
        return result;
    }


    @Override
    public void hardDeleteStory() {
        QueryWrapper<StoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("STATE", "10");
        mapper.delete(queryWrapper);
    }

}
