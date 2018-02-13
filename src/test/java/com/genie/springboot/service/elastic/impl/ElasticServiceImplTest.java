package com.genie.springboot.service.elastic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.genie.springboot.service.elastic.ElasticService;
import com.genie.utils.JsonUtil;

public class ElasticServiceImplTest extends BaseTest {

	@Autowired
	ElasticService elasticService;

	private String index = "newgenietest";
	private String type = "newtypetest";

	@Test
	public void indextTest() {
		for (int i = 300 ; i < 350; i++) {
			Date indexTime = new Date();
			String docId = String.valueOf(i);
			String user = "user" + i;
			String msg = "msg" + i;
			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("user", user);
			jsonMap.put("msg", msg);
			jsonMap.put("@timestamp", indexTime);
			jsonMap.put("smartcardId", "8757000000000007");
			jsonMap.put("regionCode", "1602");
			jsonMap.put("regionName", "南庄");
			jsonMap.put("url", "testurl");
			jsonMap.put("url_map", "人才市场首页");
			elasticService.index(index, type, docId, jsonMap);
		}
	}
	
	@Test
	public void indexTest2(){
		String index2 = "newgenietest2";
		String type2 = "newtypetest2";
		for (int i = 0 ; i < 100; i++) {
			Date indexTime = new Date();
			String user = "user" + i;
			String msg = "msg" + i;
			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("user", user);
			jsonMap.put("msg", msg);
			jsonMap.put("@timestamp", indexTime);
			jsonMap.put("smartcardId", "8757000000000007");
			jsonMap.put("regionCode", "1602");
			jsonMap.put("regionName", "南庄");
			jsonMap.put("url", "testurl");
			jsonMap.put("url_map", "人才市场首页");
			elasticService.index(index2, type2, null, jsonMap);
		}
	}

	@Test
	public void getDocTest() {
		String documentId = "101";
		GetResponse getResponse = elasticService.getDoc(index, type, documentId);
		String jsonGetResponse = JsonUtil.beanToJson(getResponse);
		System.out.println(jsonGetResponse);
		//documentId = "101"
		/*String expectJson = "{\"fields\":{},\"id\":\"101\",\"type\":\"typetest\",\"index\":\"genietest\",\"source\":{\"msg\":\"msg101\",\"user\":\"user101\"},\"version\":1,\"sourceInternal\":{\"childResources\":[]},\"sourceAsString\":\"{\\\"msg\\\":\\\"msg101\\\",\\\"user\\\":\\\"user101\\\"}\",\"sourceAsBytesRef\":{\"childResources\":[]},\"exists\":true,\"sourceEmpty\":false,\"sourceAsMap\":{\"msg\":\"msg101\",\"user\":\"user101\"},\"sourceAsBytes\":\"eyJtc2ciOiJtc2cxMDEiLCJ1c2VyIjoidXNlcjEwMSJ9\",\"fragment\":false}\"";
		Assert.assertEquals(expectJson, jsonGetResponse);*/
	}
	
	@Test
	public void updateDocTest(){
		String documentId = "101";
		Map<String, Object> jsonMap = new HashMap<>();
		String user = "updateuser101";
		String msg = "updatemsg101";
		Date indexTime = new Date();
		jsonMap.put("user", user);
		jsonMap.put("msg", msg);
		jsonMap.put("@timestamp", indexTime);
		jsonMap.put("smartcardId", "8757000000000007");
		jsonMap.put("regionCode", "1600");
		jsonMap.put("regionName", "禅城");
		jsonMap.put("url", "testupdateurl");
		jsonMap.put("url_map", "update人才市场首页");
		
		UpdateResponse updateResponse = elasticService.updateDoc(index, type, documentId, jsonMap);
		System.out.println("update response : " + updateResponse);
	}
	
	@Test
	public void deleteDocTest(){
		String documentId = "101";
		DeleteResponse deleteResponse = elasticService.deleteDoc(index, type, documentId);
		System.out.println("delete resoult : " + deleteResponse);
	}
	
	@Test
	public void searchAllTest(){
		String index2 = "newgenietest2";
		String type2 = "newtypetest2";
		String[] types = {type2};
		SearchResponse searchResponse = elasticService.searchAll(index2, types);
		System.out.println("searchResponse is : " + searchResponse);
	}
	
	@Test
	public void searchByTermTest(){
		String[] types = {type};
		SearchResponse searchResponse = elasticService.searchByTerm(index, types, "regionName", "桂城");
		System.out.println("searchResponse is : " + searchResponse);
		System.out.println("totalhits : " + searchResponse.getHits().getTotalHits());
	}
	
	@Test
	public void searchFuzzyTest(){
		String[] types = {type};
		SearchResponse searchResponse = elasticService.searchFuzzy(index, types, "regionName", "南庄", 2, 4);
		System.out.println("searchResponse is : " + searchResponse);
	}
}
