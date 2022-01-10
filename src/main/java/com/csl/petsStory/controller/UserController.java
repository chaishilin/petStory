package com.csl.petsStory.controller;

import com.csl.petsStory.utils.RedisUtil;
import com.csl.petsStory.utils.ResponseTemplate;
import com.csl.petsStory.utils.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.commands.JedisCommands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/10 14:19
 * @Description:
 */
@RestController
public class UserController {
    @RequestMapping(value = "/newUserId")
    public ResponseTemplate newUserId() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
            Date date = new Date();
            String dateStr = dateFormat.format(date);
            JedisCommands jedis = RedisUtil.getInstance();
            long count = jedis.incr(dateStr);
            String idStr = String.format("%05d",count);
            String userId = dateStr+idStr;
            return ResponseUtil.success(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("error" + e.getMessage());
        }
    }

    @RequestMapping(value = "/getUserCount")
    public ResponseTemplate getUserCount() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
            Date date = new Date();
            String dateStr = dateFormat.format(date);
            JedisCommands jedis = RedisUtil.getInstance();
            String count = jedis.get(dateStr);
            if(count == null){
                count = "0";
            }
            return ResponseUtil.success(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("error" + e.getMessage());
        }
    }
}
