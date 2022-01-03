package com.csl.petsStory.utils;

import lombok.Data;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/3 20:52
 * @Description:
 */
@Data
public class ResponseTemplate {
    private int code;
    private String msg;
    private Object data;

}