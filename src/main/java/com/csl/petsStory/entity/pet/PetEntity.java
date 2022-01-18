package com.csl.petsStory.entity.pet;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Shilin Chai
 * @Date: 2022/1/4 12:04
 * @Description: 宠物属性
 */
@Component
@Data
public class PetEntity {
    @Autowired
    private PetAttribute petAttribute;


    //大事件
    private List<String> history;

    public PetAttribute getPetAttribute() {
        return petAttribute;
    }

    public String getAgeStr() {
        int age = petAttribute.getAgeAttr().getAge();
        if(age% 12 == 0){
            return ""+age/12+"岁";
        }else if (age < 12){
            return ""+age+"个月";
        }else if(age < 36){
            return ""+age+"个月";
        }else{
            return ""+age/12+"岁";
        }
    }

    public void addAge(int age) {
        int ageOld = petAttribute.getAgeAttr().getAge();
        petAttribute.getAgeAttr().setAge(ageOld + age);
    }

    public List<String> getHistory() {
        return history;
    }

    public void addHistory(String item) {
        this.history.add(item);
    }


    public void petGrow(){
        int age = petAttribute.getAgeAttr().getAge();
        if (age < 12){
            addAge(1);
        }else if(age < 24){
            addAge(3);
        }else{
            addAge(12);
        }
    }
}
