package com.csl.petsStory.utils.IterableProcess.impl;

import com.csl.petsStory.utils.IterableProcess.IterableProcess;
import com.csl.petsStory.utils.ProcessIterator.ProcessIteratorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Shilin Chai
 * @Date: 2021/12/25 21:43
 * @Description:
 */
@Component
public class StoryTellerProcess extends IterableProcess {

    @Autowired
    private ProcessIteratorFactory iteratorFactory;

    @Override
    public void generateWorkingList(Object params) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            result.add(i);
        }
        super.setWorkingList(result);
    }

    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}
