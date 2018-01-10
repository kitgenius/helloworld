package com.genie.springboot.helloworld.controller;

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
	
	
}
