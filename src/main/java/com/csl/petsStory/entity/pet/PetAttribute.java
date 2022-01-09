package com.csl.petsStory.entity.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 22:29
 * @Description:
 */
@Component
public class PetAttribute extends BaseAttribute {
    //基本信息
    @Autowired
    private BodyAttribute body;
    //心情
    @Autowired
    private ModAttribute mod;
    //健康
    @Autowired
    private HealthAttribute health;
    //出生属性
    @Autowired
    private BornAttribute born;

    public void setBody(BodyAttribute body) {
        this.body = body;
    }

    public void setMod(ModAttribute mod) {
        this.mod = mod;
    }

    public void setHealth(HealthAttribute health) {
        this.health = health;
    }

    public void setBorn(BornAttribute born) {
        this.born = born;
    }

    public BodyAttribute getBody() {
        return body;
    }

    public ModAttribute getMod() {
        return mod;
    }

    public HealthAttribute getHealth() {
        return health;
    }

    public BornAttribute getBorn() {
        return born;
    }

    public static Map<String, List<String>> getAttributeFields(){
        Map<String, List<String>> result = new HashMap<>();
        Field[] attributes = PetAttribute.class.getDeclaredFields();
        for(Field attr:attributes){
            if(BaseAttribute.class.isAssignableFrom(attr.getType())){
                //对于其中属性
                Field[] fields = attr.getType().getDeclaredFields();
                List<String> attrList = new ArrayList<>();
                for(Field field:fields){
                    attrList.add(field.getName());
                }
                result.put(attr.getName(),attrList);
            }
        }
        return result;
    }

}
