package com.csl.petsStory.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csl.petsStory.entity.pet.BaseAttribute;
import com.csl.petsStory.entity.pet.PetAttribute;
import com.csl.petsStory.entity.pet.PetEntity;
import com.csl.petsStory.utils.RedisUtil;
import com.csl.petsStory.utils.ResponseTemplate;
import com.csl.petsStory.utils.ResponseUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.commands.JedisCommands;

import java.lang.reflect.Field;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/8 23:51
 * @Description:
 */
@RestController
public class PetController {
    @RequestMapping(value = "/petAttribute")
    public ResponseTemplate petAttribute() {
        try {
            JSONObject result = new JSONObject();
            Field[] attributes = PetAttribute.class.getDeclaredFields();
            for(Field attr:attributes){
                if(BaseAttribute.class.isAssignableFrom(attr.getType())){
                    //对于其中属性
                    Field[] fields = attr.getType().getDeclaredFields();
                    JSONArray attrJSON = new JSONArray();
                    for(Field field:fields){
                        attrJSON.add(field.getName());
                    }
                    result.put(attr.getName(),attrJSON);
                }
            }
            return ResponseUtil.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("hello error" + e.getMessage());
        }
    }

    @RequestMapping(value = "/newPet")
    public ResponseTemplate newPet(@RequestBody PetAttribute petAttribute) {
        try {
            PetEntity petEntity = new PetEntity();
            petEntity.setPetAttribute(petAttribute);
            petEntity.setAge(0);
            petEntity.setMaxAge(120);
            JedisCommands jedis = RedisUtil.getInstance();
            jedis.set("1234",JSONObject.toJSONString(petEntity));
            return ResponseUtil.success("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("hello error" + e.getMessage());
        }
    }

}
