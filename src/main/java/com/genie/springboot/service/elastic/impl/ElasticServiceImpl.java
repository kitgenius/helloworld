package com.genie.springboot.service.elastic.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.genie.springboot.service.elastic.ElasticClientFactory;
import com.genie.springboot.service.elastic.ElasticService;

@Service("elasticService")
/**
 * 
 * @author Genie
 * 实现elastic 同步接口。
 */
public class ElasticServiceImpl implements ElasticService {

	@Override
	public IndexResponse index(String index, String type, String documentId, Map<String, Object> jsonMap) {
		IndexRequest request = null;//建立索引的请求
		if(documentId == null){//无文档id则由elastic自动生成id
			request = new IndexRequest(index,type);
		}else{
			request = new IndexRequest(index,type,documentId);
		}
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
		return deleteResponse;
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
		return updateResponse;
		
	}

	@Override
	public BulkResponse bulkHandle(BulkRequest bulkRequest) {
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		BulkResponse bulkResponse = null;
		try {
			bulkResponse = client.bulk(bulkRequest);
			client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bulkResponse;
	}

	@Override
	public SearchResponse searchAll(String index, String[] types) {
		SearchRequest searchRequest = new SearchRequest(index);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.types(types);
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchResponse;
	}

	@Override
	public SearchResponse searchByTerm(String index, String[] types, String termKey, String teamValue) {
		return searchByTerm(index, types, termKey, teamValue, 0, 10);
	}

	@Override
	public SearchResponse searchByTerm(String index, String[] types, String termKey, String teamValue, int from,
			int size) {
		SearchRequest searchRequest = new SearchRequest(index);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.termQuery(termKey, teamValue));
		searchSourceBuilder.from(from);
		searchSourceBuilder.size(size);
		searchRequest.types(types);
		searchRequest.source(searchSourceBuilder);
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchResponse;
	}

	@Override
	public SearchResponse searchFuzzy(String index, String[] types, String termKey, String teamValue, int prefixLength,
			int maxExpansions) {
		SearchRequest searchRequest = new SearchRequest(index);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		MatchQueryBuilder queryBuilder = new MatchQueryBuilder(termKey, teamValue);
		queryBuilder.fuzziness(Fuzziness.AUTO);
		queryBuilder.prefixLength(prefixLength);
		queryBuilder.maxExpansions(maxExpansions);
		searchSourceBuilder.query(queryBuilder);
		searchRequest.types(types);
		searchRequest.source(searchSourceBuilder);
		RestHighLevelClient client = ElasticClientFactory.buildClient();
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchResponse;
	}
	
	

}
