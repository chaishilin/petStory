package com.csl.petsStory.entity.pet;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 21:48
 * @Description:
 */
@Data
@Component
public class BornAttribute extends BaseAttribute {
    //幸运值
    private int lucky;
    //智力
    private int intelligence;
    //体力
    private int strength;
    //魅力
    private int charm;
}
