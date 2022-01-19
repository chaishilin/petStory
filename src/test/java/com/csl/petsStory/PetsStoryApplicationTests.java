package com.csl.petsStory;

import com.csl.petsStory.service.StoryService;
import com.csl.petsStory.service.impl.StoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetsStoryApplicationTests {


	@Autowired
	private StoryService service;

	@Autowired
	private StoryServiceImpl storyService;

	@Test
	void TransStoryMapper(){
		List<Integer> res = new ArrayList<>();
		Collections.addAll(res,5,3,5,7,3,246,46,53);
		System.out.println(res);
	}

	@Test
	void contextLoads()  {
		List<Integer> happyBegin = new ArrayList<>();
		List<Integer> happyEnd = new ArrayList<>();
		List<Integer> healthBegin = new ArrayList<>();
		List<Integer> healthEnd = new ArrayList<>();
		Collections.addAll(happyBegin,7,5,5);
		Collections.addAll(happyEnd,10,10,10);
		Collections.addAll(healthBegin,5,8,1);
		Collections.addAll(healthEnd,10,10,10);

		Map<String,List<Integer>> happy = new HashMap<>();
		happy.put("begin",happyBegin);
		happy.put("end",happyEnd);

		Map<String,List<Integer>> health = new HashMap<>();
		health.put("begin",healthBegin);
		health.put("end",healthEnd);


		List<Integer> sadBegin = new ArrayList<>();
		List<Integer> sadEnd = new ArrayList<>();
		List<Integer> unhealthBegin = new ArrayList<>();
		List<Integer> unhealthEnd = new ArrayList<>();
		Collections.addAll(sadBegin,0,1,1);
		Collections.addAll(sadEnd,3,5,5);
		Collections.addAll(unhealthBegin,0,0,1);
		Collections.addAll(unhealthEnd,10,3,10);

		Map<String,List<Integer>> sad = new HashMap<>();
		sad.put("begin",sadBegin);
		sad.put("end",sadEnd);

		Map<String,List<Integer>> unhealth = new HashMap<>();
		unhealth.put("begin",unhealthBegin);
		unhealth.put("end",unhealthEnd);

		Map<String,Map<String,List<Integer>>> storys = new HashMap<>();
		storys.put("happy",happy);
		storys.put("health",health);
		storys.put("sad",sad);
		storys.put("unhealth",unhealth);




		List<Integer> sadOne = new ArrayList<>();
		List<Integer> happyOne = new ArrayList<>();
		List<Integer> healthOne = new ArrayList<>();
		List<Integer> unhealthOne = new ArrayList<>();
		Collections.addAll(sadOne,1,5,7);
		Collections.addAll(happyOne,8,5,5);
		Collections.addAll(healthOne,3,8,3);
		Collections.addAll(unhealthOne,9,0,7);

		Map<String,List<Integer>> entitys = new HashMap<>();
		entitys.put("sadOne",sadOne);
		entitys.put("happyOne",happyOne);
		entitys.put("healthOne",healthOne);
		entitys.put("unhealthOne",unhealthOne);

		calSimilarity(entitys,storys);


	}

	public double calCosine(List<Integer> a,List<Integer> b){
		assert a.size() == b.size();
		double result = 0;
		int modA = 0;
		int modB = 0;
		for(int i = 0 ; i < a.size(); i++){
			result += a.get(i)*b.get(i);
			modA += a.get(i)*a.get(i);
			modB += b.get(i)*b.get(i);
		}
		result /= (Math.sqrt(modA)*Math.sqrt(modB));
		return result;
	}

	public void calSimilarity(Map<String,List<Integer>> entitys, Map<String,Map<String,List<Integer>>> storys){
		for( String name : entitys.keySet()){
			List<Integer> entity = entitys.get(name);
			for(String attr:storys.keySet()){
				double c1 = calCosine(entity,storys.get(attr).get("begin"));
				double c2 = calCosine(entity,storys.get(attr).get("end"));
				System.out.println(name+"与属性"+attr+"的相似度:"+c1*c2+"\n");
			}
		}
	}

}
