package com.csl.petsStory.schedule;

import com.csl.petsStory.entity.UserCountEntity.UserCountEntity;
import com.csl.petsStory.mapper.UserCountMapper;
import com.csl.petsStory.service.StoryService;
import com.csl.petsStory.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import redis.clients.jedis.commands.JedisCommands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/10 14:34
 * @Description:
 */
@EnableScheduling
@Configuration
public class ScheduleTask {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserCountMapper userCountMapper;

    @Scheduled(cron = "0 0 1 * * ?")//每天凌晨执行一次
    public void deleteStory() {
        if (!"null".equals(RedisUtil.getInstance().get("deleteStory"))) {
            //如果没有线程正在删除，那么就删除
            System.err.println("开始删除" + new Date());
            RedisUtil.getInstance().setex("deleteStory", (long) 1, "1");
            //设置正在删除
            try {
                storyService.hardDeleteStory();
            } catch (Exception e) {
                e.printStackTrace();
            }
            RedisUtil.getInstance().del("deleteStory");//程序结束后取消置位
            System.err.println("删除结束");
        }
    }

    @Scheduled(cron = "0 0 2 * * ?")//每天凌晨执行一次
    public void recordUserCount() {
        if (!"null".equals(RedisUtil.getInstance().get("recordUserCount"))) {
            //如果没有线程正在删除，那么就删除
            System.err.println("日活统计");
            RedisUtil.getInstance().setex("recordUserCount", (long) 1, "1");

            try {
                Date today = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, -1);
                Date yesterday = c.getTime();
                DateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
                String dateStr = dateFormat.format(yesterday);

                JedisCommands jedis = RedisUtil.getInstance();
                String count = jedis.get(dateStr);
                if(count == null){
                    count = "0";
                }
                UserCountEntity userCountEntity = new UserCountEntity();
                userCountEntity.setDate(dateStr);
                UserCountEntity countEntity = userCountMapper.selectById(userCountEntity.getDate());
                if(countEntity == null){
                    userCountEntity.setCount(Integer.parseInt(count));
                    userCountMapper.insert(userCountEntity);
                }else{
                    countEntity.setCount(Integer.parseInt(count));
                    userCountMapper.updateById(countEntity);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            RedisUtil.getInstance().del("recordUserCount");//程序结束后取消置位
            System.err.println("日活统计结束");
        }
    }
}
