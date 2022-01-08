package com.csl.petsStory.entity.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 22:29
 * @Description:
 */
@Component
public class PetAttribute extends BaseAttribute {
    //基本信息
    @Autowired
    private BodyAttribute body;
    //心情
    @Autowired
    private ModAttribute mod;
    //健康
    @Autowired
    private HealthAttribute health;
    //出生属性
    @Autowired
    private BornAttribute born;

    public void setBody(BodyAttribute body) {
        this.body = body;
    }

    public void setMod(ModAttribute mod) {
        this.mod = mod;
    }

    public void setHealth(HealthAttribute health) {
        this.health = health;
    }

    public void setBorn(BornAttribute born) {
        this.born = born;
    }

    public BodyAttribute getBody() {
        return body;
    }

    public ModAttribute getMod() {
        return mod;
    }

    public HealthAttribute getHealth() {
        return health;
    }

    public BornAttribute getBorn() {
        return born;
    }

}
