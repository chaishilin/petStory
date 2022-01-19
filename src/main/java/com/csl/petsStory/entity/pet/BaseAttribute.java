package com.csl.petsStory.entity.pet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/8 14:34
 * @Description:
 */
public abstract class BaseAttribute {

    protected int maxNum = 10;

    public List<Integer> toArray() {
        List<Integer> result = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (BaseAttribute.class.isAssignableFrom(field.getType()) && !field.getType().equals(AgeAttribute.class)) {
                    BaseAttribute subAttr = (BaseAttribute) field.get(this);
                    result.addAll(subAttr.toArray());
                } else if (field.getType().equals(int.class) && !field.getName().contains("age")) {
                    result.add((int) field.get(this) + 1);
                }
            } catch (Exception e) {
                System.err.println("向量转换失败：" + field.getName());
            }
        }
        return result;
    }


    public BaseAttribute cal(BaseAttribute baseAttribute) {
        Class clazz = baseAttribute.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object field1 = field.get(this);
                Object field2 = field.get(baseAttribute);
                field.set(this, calType(field1, field2));
            } catch (Exception e) {
                System.err.println(field.getName());
                e.printStackTrace();
            }
        }
        return this;
    }

    private Object calType(Object o1, Object o2) throws IllegalAccessException {
        if (!o1.getClass().equals(o2.getClass())) {
            return o1;
        }
        if (o1 instanceof BaseAttribute) {
            BaseAttribute attribute1 = (BaseAttribute) o1;
            BaseAttribute attribute2 = (BaseAttribute) o2;
            attribute1.cal(attribute2);
            return attribute1;
        }
        if (o1.getClass().equals(int.class) || o1.getClass().equals(Integer.class)) {

            return limitRange((int) o1 + (int) o2);
        }
        return o1;
    }

    private int limitRange(int value) {
        int result = value;
        result = Math.min(result, maxNum);
        result = Math.max(result, 0);
        return result;
    }
}
