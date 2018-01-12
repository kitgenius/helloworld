package com.genie.springboot.helloworld.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genie.springboot.service.elastic.ElasticService;
import com.genie.springboot.service.elastic.impl.ElasticServiceImpl;

@RestController
@RequestMapping(value="/elastic")
public class ElasticController {
	private Logger logger = LoggerFactory.getLogger(ElasticController.class);
	
	//@Autowired
	ElasticService elasticService = new ElasticServiceImpl();
	
	@RequestMapping(value="/testIndex")
	public IndexResponse testIndex(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("user", request.getAttribute("user"));
		jsonMap.put("msg", request.getAttribute("msg"));
		IndexResponse indexResponse = null;
		indexResponse = elasticService.index("genietest", "doctest", "1", jsonMap);
		return indexResponse;
	}
}
