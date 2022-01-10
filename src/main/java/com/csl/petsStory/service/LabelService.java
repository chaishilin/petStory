package com.csl.petsStory.service;

import com.csl.petsStory.entity.label.LabelEntity;

import java.util.List;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/10 21:59
 * @Description:
 */
public interface LabelService {
    List<LabelEntity> getLabel(LabelEntity entity);
    String updateLabel(LabelEntity entity);
    void hardDeleteLabel();

}
