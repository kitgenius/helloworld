package com.genie.othertest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class httpclientTest {

	@Test
	public void postTest(){
		HttpPost httpPost = new HttpPost("http://172.18.252.20:8080/GetFolderContents");
		StringEntity entity = new StringEntity("<?xml version=\"1.0\" encoding=\"UTF-8\"?><GetFolderContents assetId=\"GDZX0920161012000006\" includeFolderProperties=\"N\" depth=\"1\" portalId=\"1\" clientId=\"8757002164840435\" account=\"FS2164840435\" startAt=\"1\" maxItems=\"8\" client=\"8757002164840435\" profile=\"0\"/>", "UTF-8");
		httpPost.setEntity(entity);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		
		try {
			response = httpclient.execute(httpPost);
			System.out.println(EntityUtils.toString(response.getEntity(),"UTF-8"));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
