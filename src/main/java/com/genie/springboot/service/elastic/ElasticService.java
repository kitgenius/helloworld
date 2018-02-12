package com.genie.springboot.service.elastic;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

public interface ElasticService {
	
	/**
	 * 增加索引
	 * @param index 索引名
	 * @param type 文档类型
	 * @param documentId 文档id
	 * @param jsonMap jsonMap 新增内容的键值对
	 * @return
	 */
	public IndexResponse index(String index,String type,String documentId,Map<String,Object> jsonMap);
	
	/**
	 * 删除索引
	 * @param index 索引名
	 * @return
	 */
	public DeleteIndexResponse deleteIndex(String index);
	
	/**
	 * 获取文档
	 * @param index 索引名
	 * @param type 文档类型
	 * @param documentId 文档id
	 * @return
	 */
	public GetResponse getDoc(String index,String type,String documentId);
	
	/**
	 * 删除文档
	 * @param index 索引名
	 * @param type 文档类型
	 * @param documentId 文档id
	 * @return
	 */
	public DeleteResponse deleteDoc(String index,String type,String documentId);
	
	/**
	 * 更新文档
	 * @param index 索引名
	 * @param type 文档类型
	 * @param documentId 文档id
	 * @param jsonMap jsonMap 修改内容的键值对
	 * @return
	 */
	public UpdateResponse updateDoc(String index,String type,String documentId,Map<String,Object> jsonMap);
	
	/**
	 * 批量操作，可同时增删改
	 * @param BulkRequest 操作请求，传入前将多个请求add到Bulkrequest
	 * @return
	 */
	public BulkResponse bulkHandle(BulkRequest bulkRequest);
	
	/**
	 * 查询索引下所有文档
	 * @param index 索引
	 * @param types 文档类型,多种类型
	 * @return 默认最多返回5条数据
	 */
	public SearchResponse searchAll(String index,String[] types); 
	
	/**
	 * 根据字段、值匹配索引下文档
	 * @param index 索引
	 * @param types 文档类型
	 * @param termKey 字段名
	 * @param teamValue 字段值
	 * @return 默认最多返回10条数据
	 */
	public SearchResponse searchByTerm(String index,String[] types,String termKey,String teamValue);
	
	/**
	 * 根据字段、值匹配索引下的文档，指定结果开始索引和返回条数
	 * @param index 索引
	 * @param types 文档类型
	 * @param termKey 字段名
	 * @param teamValue 字段值
	 * @param from 开始索引
	 * @param size 返回条数
	 * @return
	 */
	public SearchResponse searchByTerm(String index,String[] types,String termKey,String teamValue,int from,int size);
}
