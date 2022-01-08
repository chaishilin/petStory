package com.csl.petsStory.entity.pet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 21:35
 * @Description:
 */
@Data
@Component
public class BodyAttribute extends BaseAttribute {
    //体重
    private int weight;
    //体长
    private int size;
    //毛色
    //private List<String> colors;
    //饥饿度
    private int hanger;
    //人类亲密度
    private int friendly;
    //清洁度
    private int clean;
}
