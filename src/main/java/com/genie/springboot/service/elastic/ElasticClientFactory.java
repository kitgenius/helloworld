package com.genie.springboot.service.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genie.utils.PropertyReader;



public class ElasticClientFactory {
	
	private static Logger logger = LoggerFactory.getLogger(ElasticClientFactory.class);
	
	public static RestHighLevelClient buildClient(){
		PropertyReader propReader = new PropertyReader();
		logger.info("生产RestHighLevelClient");
		propReader.loadProps("/elastic.properties");
		String elastic_ip = propReader.getProperty("elastic.ip");
		int elastic_port = propReader.getPropertyInt("elastic.port");
		String elastic_type = propReader.getProperty("elastic.type");
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(elastic_ip, elastic_port, elastic_type)));
		logger.info("连接elasticsearch：" + elastic_ip + ":" + elastic_port + "，连接方式：" + elastic_type);
		return client;
	}
}
