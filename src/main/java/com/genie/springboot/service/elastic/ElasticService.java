package com.genie.springboot.service.elastic;

import java.util.Map;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
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
	
	//搜索后续实现 
}
