package com.csl.petsStory.service.impl;

import com.csl.petsStory.entity.label.LabelEntity;
import com.csl.petsStory.entity.pet.PetAttribute;
import com.csl.petsStory.service.LabelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/13 21:26
 * @Description:
 */
//@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LabelServiceImplTest {
    @Autowired
    private LabelService labelService;


    @Test
    void updateLabel() {
        LabelEntity entity = new LabelEntity();
        //entity.setLabelId("123456");
        entity.setAttribute(new PetAttribute());
        entity.setContent("测试标签 service");
        entity.setState("01");
        String result = labelService.updateLabel(entity);
        assert result.length()>0;
        System.out.println(labelService.getLabel(new LabelEntity()).size());
    }

    @Test
    void getLabel(){
        System.out.println(labelService.getLabel(new LabelEntity()).size());
    }
}