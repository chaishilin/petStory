package com.csl.petsStory.entity.pet;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 12:12
 * @Description:
 */
@Data
@Component
public class HealthAttribute extends BaseAttribute{
    //整体健康度
    private int totalHealth;
    //头部健康度
    private int headHealth;
    //躯干健康度
    private int bodyHealth;
    //肢体健康度
    private int footHealth;
    //尾巴健康度
    private int tailHealth;

}
