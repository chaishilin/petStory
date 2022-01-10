package com.csl.petsStory.entity.label;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.csl.petsStory.entity.pet.PetAttribute;
import lombok.Data;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/10 21:57
 * @Description:
 */
@Data
@TableName("Label")
public class LabelEntity {
    //id
    @TableId(value = "L_ID", type = IdType.INPUT)
    private String labelId;
    //内容
    @TableField("CONTENT")
    private String content;
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

}
