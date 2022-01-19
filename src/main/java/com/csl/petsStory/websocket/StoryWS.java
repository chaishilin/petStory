package com.csl.petsStory.websocket;

import com.alibaba.fastjson.JSONObject;
import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.entity.story.StoryEntity;
import com.csl.petsStory.service.StoryService;
import com.csl.petsStory.utils.RedisUtil;
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

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        addSession(id, session);
        super.logger.info("websocket add " + id);
    }


    @Override
    public void processMsg(Session session, String msg) {
        JedisCommands jedis = RedisUtil.getInstance();
        String userId = getIdMap().get(session.getId());
        if(userId == null){
            return;
        }
        String petStr = jedis.get(userId);
        PetEntity petEntity = JSONObject.parseObject(petStr,PetEntity.class);
        StoryService storyService = getApplicationContext().getBean(StoryService.class);
        StoryEntity storyEntity = storyService.randomSelectStoryItem(petEntity);
        if(storyEntity == null){
            sendMsg(userId,"你意外夭折了");
            removeSession(session);
        }else{
            petEntity.getPetAttribute().cal(storyEntity.getAttributeFromStr());
            petEntity.petGrow();
            jedis.set(userId,JSONObject.toJSONString(petEntity));
            sendMsg(userId,"你"+petEntity.getAgeStr() + "啦！"+ storyEntity.getContent());
            if(storyEntity.getTag().equals("死亡")){
                removeSession(session);
            }
        }
    }

    private PetEntity calAttr(PetEntity petEntity, StoryEntity storyEntity) {
        petEntity.getPetAttribute().cal(storyEntity.getAttributeFromStr());
        return petEntity;
    }
}

