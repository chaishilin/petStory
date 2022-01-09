package com.csl.petsStory.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.commands.JedisCommands;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/8 22:10
 * @Description:
 */
@Component
public class NumberGenerator {

    public String generateId(String name) {
        JedisCommands jedis = RedisUtil.getInstance();
        long id = jedis.incr(name);
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String idStr = String.format("%05d",id);
        return date+idStr;
    }
}
