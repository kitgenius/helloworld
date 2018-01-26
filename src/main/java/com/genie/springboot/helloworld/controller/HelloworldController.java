package com.genie.springboot.helloworld.controller;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genie.springboot.helloworld.model.HelloModel;

@RestController
@RequestMapping(value="/springboot")
public class HelloworldController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloworldController.class);
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String index(){
		logger.info("/springboot/hello");
		return "hello world Genie";
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public HelloModel test(){
		logger.info("/springboot/test");
		HelloModel helloModel=new HelloModel();
		helloModel.setStr1("String 1");
		helloModel.setStr2("String 2");
		helloModel.setInt1(1);
		return helloModel;
	}
	
	@RequestMapping(value="/testUpload",method=RequestMethod.GET)
	public String testUpload(){
		
        String pic = "G:\\temp\\gg123.png";
		
		File picFile = new File(pic);
		HttpPost httpPost = new HttpPost("http://172.18.128.86:8080/faith_web/image/upload");// 创建httpPost 
		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		//上传文件，传参数名、文件
		entityBuilder.addBinaryBody("file", picFile);
		//参数
		entityBuilder.addTextBody("type", "0",ContentType.TEXT_PLAIN.withCharset("UTF-8"));
		httpPost.setEntity(entityBuilder.build());
		
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        
        try {
			response = httpclient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			return EntityUtils.toString(responseEntity);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
