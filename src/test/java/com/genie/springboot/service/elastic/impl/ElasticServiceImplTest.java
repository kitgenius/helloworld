package com.genie.springboot.service.elastic.impl;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.genie.springboot.service.elastic.ElasticService;
import com.genie.utils.JsonUtil;

public class ElasticServiceImplTest extends BaseTest {

	@Autowired
	ElasticService elasticService;

	private String index = "genietest";
	private String type = "typetest";

	@Test
	public void indextTest() {
		for (int i = 0 + 100; i < 200; i++) {
			String docId = String.valueOf(i);
			String user = "user" + i;
			String msg = "msg" + i;
			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("user", user);
			jsonMap.put("msg", msg);
			elasticService.index(index, type, docId, jsonMap);
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
}
