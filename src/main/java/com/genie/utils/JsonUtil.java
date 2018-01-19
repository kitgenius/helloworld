package com.genie.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	/**
	 * 传入任意一个 object对象生成一个指定规格的字符串
	 * 
	 * @param object
	 *            任意对象
	 * @return String
	 */
	public static String objectToJson(Object object) {
		ObjectMapper objMapper = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = objMapper.writeValueAsString(object);
			return jsonStr;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "transla error";
		}
		
	}

	
	public static Object jsonToBean(String jsonStr,Class clazz){
		ObjectMapper objMapper = new ObjectMapper();
		Object obj;
		try {
			obj = objMapper.readValue(jsonStr, clazz);
			return obj;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 传入任意一个 Javabean对象生成一个指定规格的字符串
	 * 
	 * @param bean
	 *            bean对象
	 * @return String "{}"
	 */
	public static String beanToJson(Object obj) {
		ObjectMapper objMapper = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = objMapper.writeValueAsString(obj);
			return jsonStr;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "transla error";
		}
	}

	/**
	 * 通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串
	 * 
	 * @param list
	 *            列表对象
	 * @return String "[{},{}]"
	 */
	public static String listToJson(List<?> list) {
		
		return null;
	}

}