package com.genie.othertest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;

public class PDFtoImageTest {
	@Test
	public void myTest(){
		File file = new File("F:\\temp\\pdfbox\\国务院关于修改《建设项目环境保护管理条例》的决定.pdf");  
		HttpPost httpPost = new HttpPost("http://172.18.128.86:8080/faith_web/image/upload");
		MultipartEntityBuilder multiEntity = MultipartEntityBuilder.create();
        try { 
            PDDocument doc = PDDocument.load(file);  
            PDFRenderer renderer = new PDFRenderer(doc);  
            int pageCount = doc.getNumberOfPages();  
            //List<String> imageList = new ArrayList<String>();
            for (int i = 0; i < pageCount; i++) { 
            	String outputFileName = "F:\\temp\\pdfbox\\wortopdf_2_" + i +".png";
            	//imageList.add(outputFileName);
                // 方式1,第二个参数是设置缩放比(即像素)  
                BufferedImage image = renderer.renderImageWithDPI(i, 72);  
                // 方式2,第二个参数是设置缩放比(即像素)  
                // BufferedImage image = renderer.renderImage(i, 2.5f);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageOutputStream imageOutput = ImageIO.createImageOutputStream(os);
                ImageIO.write(image, "PNG", imageOutput);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                multiEntity.addBinaryBody("file", is);
            }
            multiEntity.addTextBody("type", "0",ContentType.TEXT_PLAIN.withCharset("UTF-8"));
            httpPost.setEntity(multiEntity.build());
            
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            
            response = httpclient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			
			System.out.println(EntityUtils.toString(responseEntity));
			
        } catch (IOException e) {
            e.printStackTrace();  
        }
	}
}
