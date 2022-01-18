package com.csl.petsStory.entity.pet;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/18 9:39
 * @Description:
 */
@Data
@Component
public class AgeAttribute extends BaseAttribute {

    AgeAttribute(){
        super.maxNum = 12*100;
        maxAge = super.maxNum;
    }
    //年龄范围
    private int age;


    //最大年龄
    private int maxAge;


    public boolean reachLimit(){
        return age >= maxAge;
    }

}
