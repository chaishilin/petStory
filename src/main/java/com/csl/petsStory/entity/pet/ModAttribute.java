package com.csl.petsStory.entity.pet;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 12:20
 * @Description: 心情属性
 */
@Data
@Component
public class ModAttribute extends BaseAttribute {
    private int happy;
    private int angry;
    private int terrified;

    public ModAttribute(){
        int defaultValue = 5;
        happy = defaultValue;
        angry = defaultValue;
        terrified = defaultValue;
    }
}
