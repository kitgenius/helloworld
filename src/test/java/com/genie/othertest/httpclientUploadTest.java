package com.genie.othertest;

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
import org.junit.Test;

public class httpclientUploadTest {
	@Test
	public void imageUploadTest(){

		
        String pic = "F:\\temp\\pdfbox\\wortopdf_2_1.png";
		
		File picFile = new File(pic);
		HttpPost httpPost = new HttpPost("http://172.18.128.86:8080/faith_web/image/upload");// 创建httpPost 
		MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
		//上传文件，传参数名、文件
		entityBuilder.addBinaryBody("file", picFile);
		//参数
		entityBuilder.addTextBody("type", "1",ContentType.TEXT_PLAIN.withCharset("UTF-8"));
		httpPost.setEntity(entityBuilder.build());
		
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        
        try {
			response = httpclient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			System.out.println(EntityUtils.toString(responseEntity));
			return ;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	
	}
}
