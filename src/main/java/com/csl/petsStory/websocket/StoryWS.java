package com.csl.petsStory.websocket;

import com.alibaba.fastjson.JSONObject;
import com.csl.petsStory.entity.pet.BodyAttribute;
import com.csl.petsStory.entity.pet.BornAttribute;
import com.csl.petsStory.entity.pet.HealthAttribute;
import com.csl.petsStory.entity.pet.ModAttribute;
import com.csl.petsStory.entity.pet.PetAttribute;
import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.entity.story.StoryEntity;
import com.csl.petsStory.mapper.StoryMapper;
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
        getNewPet(id);
    }


    @Override
    Object processMsg(Session session, String msg) {
        PetEntity petEntity = new PetEntity();
        PetAttribute petAttribute = new PetAttribute();
        petAttribute.setBody(new BodyAttribute());//pet存在redis
        petAttribute.setBorn(new BornAttribute());
        petAttribute.setHealth(new HealthAttribute());
        petAttribute.setMod(new ModAttribute());
        petAttribute.getBody().setClean(Integer.parseInt(msg));
        petAttribute.getBorn().setIntelligence(9);
        petEntity.setPetAttribute(petAttribute);
        StoryEntity sEntity = new StoryEntity();
        //sEntity.setAttribute("{\"body\":{\"clean\":1,\"friendly\":2,\"hanger\":4,\"size\":6,\"weight\":1},\"born\":{\"charm\":1,\"intelligence\":2,\"lucky\":0,\"strength\":0},\"health\":{\"bodyHealth\":0,\"footHealth\":1,\"headHealth\":0,\"tailHealth\":0,\"totalHealth\":0},\"mod\":{\"angry\":5,\"defaultValue\":5,\"happy\":5,\"sad\":5,\"terrified\":5}}");
        PetEntity entity = calAttr(petEntity,sEntity);
        sendMsg("1234", JSONObject.toJSONString(entity));
        return null;
    }

    private PetEntity calAttr(PetEntity petEntity, StoryEntity storyEntity) {
        petEntity.getPetAttribute().cal(storyEntity.getAttribute());
        return petEntity;
    }

    private void getNewPet(String id){
        JedisCommands jedis = RedisUtil.getInstance();
        PetEntity newPet = getApplicationContext().getBean(PetEntity.class);
        jedis.set(id,JSONObject.toJSONString(newPet));
    }
}

