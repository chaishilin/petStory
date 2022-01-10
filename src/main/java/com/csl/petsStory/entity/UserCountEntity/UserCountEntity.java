package com.csl.petsStory.entity.UserCountEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/10 14:45
 * @Description:
 */
@Data
@TableName("UserCount")
public class UserCountEntity {
    @TableId(value = "DATE",type = IdType.INPUT)
    private String date;
    @TableField("COUNT")
    private int count;

}
