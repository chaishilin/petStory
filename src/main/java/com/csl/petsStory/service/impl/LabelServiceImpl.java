package com.csl.petsStory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csl.petsStory.entity.label.LabelEntity;
import com.csl.petsStory.mapper.LabelMapper;
import com.csl.petsStory.service.LabelService;
import com.csl.petsStory.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/10 22:00
 * @Description:
 */
@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private NumberGenerator numberGenerator;
    @Autowired
    private LabelMapper mapper;
    @Override
    public List<LabelEntity> getLabel(LabelEntity entity) {
        QueryWrapper<LabelEntity> queryWrapper = new QueryWrapper<>();
        if (entity.getContent() != null && !"".equals(entity.getContent())) {
            queryWrapper.like("CONTENT", entity.getContent());
        }
        if (entity.getState() != null && !"".equals(entity.getState())) {
            queryWrapper.eq("STATE", entity.getState());
        }
        queryWrapper.ne("STATE","10");
        return mapper.selectList(queryWrapper);
    }

    @Override
    public String updateLabel(LabelEntity entity) {
        String id;
        entity.setAttributeStr(entity.getAttributeStr());
        if (entity.getLabelId() == null || "".equals(entity.getLabelId())) {
            id = numberGenerator.generateId("labelId");
            entity.setLabelId(id);
            mapper.insert(entity);
        } else {
            mapper.updateById(entity);
            id = entity.getLabelId();
        }
        return id;
    }

    @Override
    public void hardDeleteLabel() {
        QueryWrapper<LabelEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("STATE","10");
        mapper.delete(queryWrapper);
    }
}
