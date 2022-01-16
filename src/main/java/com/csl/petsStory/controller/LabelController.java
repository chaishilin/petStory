package com.csl.petsStory.controller;

import com.csl.petsStory.entity.label.LabelEntity;
import com.csl.petsStory.service.LabelService;
import com.csl.petsStory.utils.ResponseTemplate;
import com.csl.petsStory.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/10 21:56
 * @Description:
 */
@RestController
public class LabelController {
    @Autowired
    private LabelService service;
    @RequestMapping(value = "/getLabel")
    public ResponseTemplate getLabel(@RequestBody LabelEntity entity) {
        try {
            return ResponseUtil.success(service.getLabel(entity));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("error" + e.getMessage());
        }
    }

    @RequestMapping(value =  "/updateLabel")
    public ResponseTemplate updateLabel(@RequestBody LabelEntity entity) {
        try {
            String result = service.updateLabel(entity);
            return ResponseUtil.success("新增或修改成功！",result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("error" + e.getMessage());
        }
    }
}
