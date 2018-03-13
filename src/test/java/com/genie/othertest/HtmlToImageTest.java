package com.genie.othertest;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import gui.ava.html.image.generator.HtmlImageGenerator;
import gui.ava.html.link.LinkInfo;

public class HtmlToImageTest {
	@Test
	public void myTest(){
		String urlStr = "http://172.18.252.6:9090/iepgm/login.action";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(urlStr);
		String htmlStr = "<div class=\"box box--padded tabs__content\"><section class=\"app-author-review\" itemscope=\"\" itemprop=\"review\" itemtype=\"http://schema.org/Review\"><div class=\"app-author-review__content app-author-review__content--wide\" itemprop=\"reviewBody\"><p>This is a powerful and easy-to-use practical application which will help you convert WebPages, Mht files and Html to all image formats including PNG, JPG,GIF,BMP,TIF.</p><p>It captures the whole WebPages' content together with all pictures and other embedded objects, without dependence from the size of page, even if it much longer than screen resolution ( vertical and/or horizontal), all page is kept as is.</p></div></section></div>";
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			//htmlStr = EntityUtils.toString(httpResponse.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		System.out.println(htmlStr);
		imageGenerator.loadHtml(htmlStr);
		//imageGenerator.loadUrl(urlStr);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageGenerator.getBufferedImage();
		String imageFilePath = "F:\\temp\\html2image\\testStr.jpg";
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageGenerator.saveAsImage(imageFilePath);
		
	}
	
	@Test
	public void mytest2(){
		
	}
}
