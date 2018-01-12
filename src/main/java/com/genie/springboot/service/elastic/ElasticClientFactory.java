package com.genie.springboot.service.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import com.genie.utils.PropertyReader;



public class ElasticClientFactory {
	public static RestHighLevelClient buildClient(){
		PropertyReader propReader = new PropertyReader();
		propReader.loadProps("elastic.properties");
		String elastic_ip = propReader.getProperty("elastic.ip");
		int elastic_port = propReader.getPropertyInt("elastic.port");
		String elastic_type = propReader.getProperty("elastic.type");
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(elastic_ip, elastic_port, elastic_type)));
		return client;
	}
}
