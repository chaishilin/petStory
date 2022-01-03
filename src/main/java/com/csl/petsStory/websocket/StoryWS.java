package com.csl.petsStory.websocket;
import com.csl.petsStory.utils.IterableProcess.impl.NumberGenerateProcess;
import com.csl.petsStory.utils.IterableProcess.impl.StoryTellerProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


/**
 * @author: Shilin Chai
 * @Date: 2021/12/21 9:41
 * @Description:
 */

@Component
@ServerEndpoint("/story/{id}")
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //todo 有没有方法强制要求子类实现至少一个带有@onopen注解的函数？
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        addSession(id,session);
        super.logger.info("websocket add "+id);
    }
}

