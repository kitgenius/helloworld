package com.genie.springboot.service.elastic.impl;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.genie.springboot.service.elastic.ElasticClientFactory;
import com.genie.springboot.service.elastic.ElasticService;

@Service("elasticService")
public class ElasticServiceImpl implements ElasticService {

	@Override
	public IndexResponse index(String index, String type, String documentId, Map<String, Object> jsonMap) {
		IndexRequest request = new IndexRequest(index,type,documentId);//建立索引的请求
		request.source(jsonMap);//文档源
		request.timeout("2s");//设置2秒超时
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		IndexResponse indexResponse=null;
		try {
			indexResponse = client.index(request);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return indexResponse;
	}

	@Override
	public DeleteIndexResponse deleteIndex(String index) {
		DeleteIndexRequest request = new DeleteIndexRequest(index);
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		DeleteIndexResponse deleteResponse = null;
		try {
			deleteResponse = client.indices().deleteIndex(request);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleteResponse;
	}

	@Override
	public GetResponse getDoc(String index, String type, String documentId) {
		GetRequest request = new GetRequest(index, type, documentId);
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		GetResponse getResponse = null;
		try {
			getResponse = client.get(request);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getResponse;
	}

	@Override
	public DeleteResponse deleteDoc(String index, String type, String documentId) {
		DeleteRequest request = new DeleteRequest(index, type, documentId);
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		DeleteResponse deleteResponse = null;
		try {
			deleteResponse = client.delete(request);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UpdateResponse updateDoc(String index, String type, String documentId,Map<String,Object> jsonMap) {
		UpdateRequest request = new UpdateRequest(index, type, documentId);
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		UpdateResponse updateResponse = null;
		request.doc(jsonMap);
		try {
			updateResponse = client.update(request);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
