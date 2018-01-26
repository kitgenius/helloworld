package com.genie.springboot.helloworld;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.genie.springboot.helloworld","com.genie.springboot.service"})
public class HelloworldApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String pic = "F:\\temp\\123.png";
		
		File picFile = new File(pic);
		HttpPost httpPost = new HttpPost("http://172.18.128.86:8080/faith_web/image/upload");// 创建httpPost 
		
		HttpEntity entity = 
		httpPost.setEntity(entity);
		
		SpringApplication.run(HelloworldApplication.class, args);
		
	}
	
	
	
}
