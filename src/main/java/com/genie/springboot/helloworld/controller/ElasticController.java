package com.genie.springboot.helloworld.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genie.springboot.service.elastic.ElasticService;
import com.genie.springboot.service.elastic.impl.ElasticServiceImpl;

@RestController
@RequestMapping(value="/elastic")
public class ElasticController {
	private Logger logger = LoggerFactory.getLogger(ElasticController.class);
	
	@Autowired
	ElasticService elasticService;
	
	@RequestMapping(value="/testIndex",method=RequestMethod.GET)
	public IndexResponse testIndex(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("user", request.getParameter("user"));
		jsonMap.put("msg", request.getParameter("msg"));
		IndexResponse indexResponse = null;
		indexResponse = elasticService.index("genietest", "doctest", "3", jsonMap);
		return indexResponse;
	}
	
	@RequestMapping(value="/testDeleteIndex",method=RequestMethod.GET)
	public DeleteIndexResponse testDeleteIndex(HttpServletRequest request,HttpServletResponse response){
		String index = request.getParameter("index");
		DeleteIndexResponse deleteIndexResponse = null;
		deleteIndexResponse = elasticService.deleteIndex(index);
		return deleteIndexResponse;
	}
}
