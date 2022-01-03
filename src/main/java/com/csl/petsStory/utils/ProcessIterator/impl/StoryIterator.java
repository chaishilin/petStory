package com.csl.petsStory.utils.ProcessIterator.impl;


import com.csl.petsStory.utils.ProcessIterator.ProcessIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2021/12/25 17:35
 * @Description:
 */
@Component(value = "StoryIterator")
public class StoryIterator extends ProcessIterator {

    @Override
    public String process(Object o) {
        try {
            Thread.sleep(100);
            return "第"+o.toString()+"年，";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
