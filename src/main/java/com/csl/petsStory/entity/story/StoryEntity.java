package com.csl.petsStory.entity.story;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.csl.petsStory.entity.pet.PetAttribute;
import lombok.Data;

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
    //最小发生年龄
    @TableField("MIN_AGE")
    private int minAge;
    //最大发生年龄
    @TableField("MAX_AGE")
    private int maxAge;
    //属性变化相关
    @TableField(exist = false)
    private PetAttribute attribute;

    //属性变化值
    @TableField("ATTRIBUTE")
    private String attributeStr;

    @TableField("STATE")
    private String state;

    public String getAttributeStr(){
        return JSONObject.toJSONString(attribute);
    }

    public PetAttribute getAttribute(){
        return JSONObject.parseObject(attributeStr,PetAttribute.class);
    }

    //触发属性下限
    @TableField(exist = false)
    private PetAttribute attributeLow;

    //触发属性下限
    @TableField("ATTRIBUTE_LOW")
    private String attributeLowStr;

    public String getAttributeLowStr(){
        return JSONObject.toJSONString(attributeLow);
    }

    public PetAttribute getAttributeLow(){
        return JSONObject.parseObject(attributeLowStr,PetAttribute.class);
    }

    //触发属性上限
    @TableField(exist = false)
    private PetAttribute attributeHigh;

    //触发属性上限
    @TableField("ATTRIBUTE_HIGH")
    private String attributeHighStr;

    public String getAttributeHighStr(){
        return JSONObject.toJSONString(attributeHigh);
    }

    public PetAttribute getAttributeHigh(){
        return JSONObject.parseObject(attributeHighStr,PetAttribute.class);
    }
}
