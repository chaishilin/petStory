package com.csl.petsStory.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Shilin Chai
 * @Date: 2021/12/20 16:12
 * @Description:
 */
@Component
public abstract class BaseWebSocket {
    protected Logger logger = LoggerFactory.getLogger(BaseWebSocket.class);

    //静态变量，对象之间共享
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();//key->session
    private static Map<String, String> idMap = new ConcurrentHashMap<>();//sessionId->key

    public static ConcurrentHashMap<String, Session> getConcurrentHashMap() {
        return new ConcurrentHashMap<>(sessionMap);//返回一份拷贝
    }

    public static Map<String, String> getIdMap() {
        return new ConcurrentHashMap<>(idMap);//返回一份拷贝
    }

    public static void addSession(String key, Session session) {
        if (key != null && !"".equals(key)) {
            sessionMap.put(key, session);
            idMap.put(session.getId(), key);
        } else {
            sessionMap.put(session.getId(), session);
            idMap.put(session.getId(), session.getId());
        }
    }

    public static void removeSession(Session session) {
        sessionMap.remove(idMap.get(session.getId()));
        idMap.remove(session.getId());
    }



    //上下文
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        BaseWebSocket.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @OnError
    public void onError(Session session, Throwable error) {
        removeSession(session);
        logger.info("websocket remove " + session.getId());
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        logger.info("websocket get msg:{" + msg + "} from " + session.getId());
        processMsg(session, msg);
    }

    abstract Object processMsg(Session session, String msg);

    @OnClose
    public void onClose(Session session) {
        removeSession(session);
        logger.info("websocket remove " + session.getId());
    }

    public void sendMsg(String key, String msg) {
        sendMsg(getConcurrentHashMap().get(key), msg);
    }

    public void sendMsg(Session session, String msg) {
        try {
            session.getBasicRemote().sendText(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void boardCastMsg(String msg) {
        for (Session session : sessionMap.values()) {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
