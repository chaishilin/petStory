package com.csl.petsStory.websocket;

import com.alibaba.fastjson.JSONObject;
import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.entity.story.StoryEntity;
import com.csl.petsStory.service.StoryService;
import com.csl.petsStory.utils.IterableProcess.impl.StoryTellerProcess;
import com.csl.petsStory.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.commands.JedisCommands;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


/**
 * @author: Shilin Chai
 * @Date: 2021/12/21 9:41
 * @Description:
 */

@ServerEndpoint("/story/{id}")
@Component
public class StoryWS extends BaseWebSocket {
    @Autowired
    private StoryTellerProcess process;

    public void run() {
        try {
            process.generateWorkingList(new Object());
            for (Object processResult : process) {
                String msg = (String) processResult;
                sendMsg("1234", msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        addSession(id, session);
        super.logger.info("websocket add " + id);
    }


    @Override
    Object processMsg(Session session, String msg) {
        JedisCommands jedis = RedisUtil.getInstance();
        String userId = getIdMap().get(session.getId());
        String petStr = jedis.get(userId);
        PetEntity petEntity = JSONObject.parseObject(petStr,PetEntity.class);
        StoryService storyService = getApplicationContext().getBean(StoryService.class);
        StoryEntity storyEntity = storyService.randomSelectStoryItem(petEntity);
        petEntity.getPetAttribute().cal(storyEntity.getAttributeFromStr());
        petEntity.petGrow();
        jedis.set(userId,JSONObject.toJSONString(petEntity));
        if(petEntity.getAge() >= petEntity.getMaxAge()){
            sendMsg(userId,"你"+petEntity.getAgeStr() + "啦！趴在小窝窝里面睡着去天堂啦");
            removeSession(session);
        }else{
            sendMsg(userId,"你"+petEntity.getAgeStr() + "啦！"+ storyEntity.getContent());
        }
        return null;
    }

    private PetEntity calAttr(PetEntity petEntity, StoryEntity storyEntity) {
        petEntity.getPetAttribute().cal(storyEntity.getAttributeFromStr());
        return petEntity;
    }
}

