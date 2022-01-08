package com.csl.petsStory.entity.pet;

import lombok.Data;
import org.springframework.cglib.transform.AbstractTransformTask;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/8 14:34
 * @Description:
 */
public abstract class BaseAttribute {
    protected final int maxNum = 10;

    public BaseAttribute cal(BaseAttribute baseAttribute) throws IllegalAccessException {
        Class clazz = baseAttribute.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            Object field1 = field.get(this);
            Object field2 = field.get(baseAttribute);
            try {
                field.set(this,calType(field1,field2));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return this;
    }

    private Object calType(Object o1,Object o2) throws IllegalAccessException {
        if(!o1.getClass().equals(o2.getClass())){
            return o1;
        }
        if( o1 instanceof BaseAttribute){
            BaseAttribute attribute1 = (BaseAttribute) o1;
            BaseAttribute attribute2 = (BaseAttribute) o2;
            attribute1.cal(attribute2);
            return attribute1;
        }
        if(o1.getClass().equals(int.class) || o1.getClass().equals(Integer.class)){

            return limitRange((int)o1+(int)o2);
        }
        return o1;
    }

    private int limitRange(int value){
        int result = value;
        result = Math.min(result,maxNum);
        result = Math.max(result,0);
        return result;
    }
}
