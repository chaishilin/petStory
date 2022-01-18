package com.csl.petsStory.controller;

import com.csl.petsStory.entity.story.StoryEntity;
import com.csl.petsStory.service.StoryService;
import com.csl.petsStory.utils.RedisUtil;
import com.csl.petsStory.utils.ResponseTemplate;
import com.csl.petsStory.utils.ResponseUtil;
import com.csl.petsStory.websocket.StoryWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.commands.JedisCommands;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/3 20:46
 * @Description:
 */
@RestController
public class StoryController {
    @Autowired
    private StoryWS storyWS;
    @Autowired
    private StoryService storyService;

    @RequestMapping(value = "/getStory")
    public ResponseTemplate getStory(@RequestBody StoryEntity entity, HttpServletRequest request) {
        try {

            return ResponseUtil.success(storyService.getStory(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("hello error" + e.getMessage());
        }
    }

    @RequestMapping(value = "/updateStory")
    public ResponseTemplate updateStory(@RequestBody StoryEntity entity, HttpServletRequest request) {
        try {
            String id = storyService.updateStory(entity);
            JedisCommands jedis = RedisUtil.getInstance();
            jedis.del("storyList");
            return ResponseUtil.success("addStory ok " +id );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("hello error" + e.getMessage());
        }
    }
}
