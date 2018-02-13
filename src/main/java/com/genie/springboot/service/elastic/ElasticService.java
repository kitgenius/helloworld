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
	 * 根据字段、值匹配索引下文档，只能匹配数字与英文
	 * @param index 索引
	 * @param types 文档类型
	 * @param termKey 字段名
	 * @param teamValue 字段值
	 * @return 默认最多返回10条数据
	 */
	public SearchResponse searchByTerm(String index,String[] types,String termKey,String teamValue);
	
	/**
	 * 根据字段、值匹配索引下的文档，只能匹配数字与英文，指定结果开始索引和返回条数
	 * @param index 索引
	 * @param types 文档类型
	 * @param termKey 字段名
	 * @param teamValue 字段值
	 * @param from 开始索引
	 * @param size 返回条数
	 * @return
	 */
	public SearchResponse searchByTerm(String index,String[] types,String termKey,String teamValue,int from,int size);
	
	/**
	 * 根据字段、值匹配索引下的文档，模糊查询，可查询中文
	 * fuzzy query ：主要根据fuzziniess和prefix_length进行匹配distance查询。根据type不同distance计算不一样。
     * numeric类型的distance类似于区间，string类型则依据Levenshtein distance，即从一个stringA变换到另一个stringB，需要变换的最小字母数。
     * 如果指定为AUTO，则根据term的length有以下规则：
     * 0-1：完全一致
     * 1-4：1
     * >4：2
     * 推荐指定prefix_length，表明这个范围的字符需要精准匹配，如果不指定prefix_lengh和fuzziniess参数，该查询负担较重。
	 * @param index 索引
	 * @param types 文档类型
	 * @param termKey 字段名
	 * @param teamValue 字段值
	 * @param prefixLength 匹配前缀长度，需要精准匹配
	 * @param maxExpansions 匹配扩展到的最大条数
	 * @return
	 */
	public SearchResponse searchFuzzy(String index,String[] types,String termKey,String teamValue,int prefixLength,int maxExpansions);
}
