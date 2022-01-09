package com.csl.petsStory.entity.pet;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 21:35
 * @Description:
 */
@Data
@Component
public class BodyAttribute extends BaseAttribute {
    //体重 g
    private int weight;
    //毛色
    //private List<String> colors;
    //饥饿度
    private int hanger;
    //人类亲密度
    private int friendly;
    //清洁度
    private int clean;

    public BodyAttribute(){
        weight = 1;
        hanger = 10;
        friendly = 5;
        clean = 10;
    }
}
