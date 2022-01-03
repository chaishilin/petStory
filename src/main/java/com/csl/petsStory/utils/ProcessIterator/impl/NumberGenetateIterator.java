package com.csl.petsStory.utils.ProcessIterator.impl;

import com.csl.petsStory.utils.ProcessIterator.ProcessIterator;
import org.springframework.stereotype.Component;

/**
 * @author: Shilin Chai
 * @Date: 2021/12/21 9:58
 * @Description:
 */
@Component(value = "NumberGenetateIterator")
public class NumberGenetateIterator extends ProcessIterator {


    @Override
    public String process(Object o) {
        try {
            Thread.sleep(100);
            return o.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

}
