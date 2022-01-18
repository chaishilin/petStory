package com.csl.petsStory.entity.story;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.csl.petsStory.entity.pet.PetAttribute;
import com.csl.petsStory.entity.pet.PetEntity;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 22:21
 * @Description:
 */
@Data
@TableName("Story")
public class StoryEntity {
    //id
    @TableId(value = "S_ID",type = IdType.INPUT)
    private String storyId;
    //内容
    @TableField("CONTENT")
    private String content;
    //标签
    @TableField("TAG")
    private String tag;
    //属性变化相关
    @TableField(exist = false)
    private PetAttribute attribute;

    //属性变化值
    @TableField("ATTRIBUTE_STR")
    private String attributeStr;

    @TableField("STATE")
    private String state;

    public PetAttribute getAttribute(){
        return getAttributeFromStr();
    }

    public String getAttributeStr(){
        return JSONObject.toJSONString(attribute);
    }

    public PetAttribute getAttributeFromStr(){
        attribute =  JSONObject.parseObject(attributeStr,PetAttribute.class);
        return attribute;
    }

    //触发属性下限
    @TableField(exist = false)
    private PetAttribute attributeLow;

    //触发属性下限
    @TableField("ATTRIBUTE_LOW_STR")
    private String attributeLowStr;

    public PetAttribute getAttributeLow(){
        return getAttributeLowFromStr();
    }

    public String getAttributeLowStr(){
        return JSONObject.toJSONString(attributeLow);
    }

    public PetAttribute getAttributeLowFromStr(){
        attributeLow =  JSONObject.parseObject(attributeLowStr,PetAttribute.class);
        return attributeLow;
    }

    //触发属性上限
    @TableField(exist = false)
    private PetAttribute attributeHigh;

    //触发属性上限
    @TableField("ATTRIBUTE_HIGH_STR")
    private String attributeHighStr;

    public PetAttribute getAttributeHigh(){
        return getAttributeHighFromStr();
    }
    public String getAttributeHighStr(){
        return JSONObject.toJSONString(attributeHigh);
    }
    public PetAttribute getAttributeHighFromStr(){
        attributeHigh = JSONObject.parseObject(attributeHighStr,PetAttribute.class);
        return attributeHigh;
    }

    //判断宠物是否适合本故事
    public boolean StoryItemSelectAble(PetEntity petEntity){

        Map<String, List<String>> petAttrubites = PetAttribute.getAttributeFields();
        for(String key : petAttrubites.keySet()){
            for(String attr : petAttrubites.get(key)){
                try {
                    Field attrFromPet = PetAttribute.class.getDeclaredField(key);
                    Field attrField = attrFromPet.getType().getDeclaredField(attr);
                    attrFromPet.setAccessible(true);
                    attrField.setAccessible(true);
                    Object attrLow = attrFromPet.get(getAttributeLowFromStr());
                    Object attrHigh = attrFromPet.get(getAttributeHighFromStr());
                    Object attrPet = attrFromPet.get(petEntity.getPetAttribute());
                    int valueLow = (int)attrField.get(attrLow);
                    int valueHigh = (int)attrField.get(attrHigh);
                    int valuePet = (int)attrField.get(attrPet);
                    if(valuePet < valueLow || valuePet > valueHigh ){
                        return false;
                    }
                    //System.out.println(attr + ":" + valueLow + "~" + valueHigh + "!!" + valuePet);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
