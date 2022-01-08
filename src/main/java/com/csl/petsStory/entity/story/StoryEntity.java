package com.csl.petsStory.entity.story;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.csl.petsStory.entity.pet.PetAttribute;
import lombok.Data;
import org.springframework.stereotype.Component;

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
    @TableField("ATTRIBUTE")
    private String attribute;
}
