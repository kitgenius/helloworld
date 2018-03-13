package com.genie.othertest;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;

public class PDFtoImageTest {
	@Test
	public void myTest(){
		File file = new File("F:\\temp\\pdfbox\\bigtest.pdf");  
		HttpPost httpPost = new HttpPost("http://172.18.128.86:8080/faith_web/image/upload");
		MultipartEntityBuilder multiEntity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);//设置正确模式，否则中文会出现乱码
		//multiEntity.setCharset(Charset.forName("UTF-8"));
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            //List<String> imageList = new ArrayList<String>();
            for (int i = 0; i < pageCount; i++) {
            	String outputFileName = "bigtest_" + i +".png";
            	//imageList.add(outputFileName);
                // 方式1,第二个参数是设置缩放比(即像素)，这里设置72dpi
                //BufferedImage image = resize(renderer.renderImageWithDPI(i, 96),0,842);//压缩图片尺寸，相对于直接设置dpi，压缩后效果要差
                BufferedImage image = renderer.renderImageWithDPI(i, 72);
                // 方式2,第二个参数是设置缩放比(即像素)，这里设置2.5*72dpi
                // BufferedImage image = renderer.renderImage(i, 2.5f);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageOutputStream imageOutput = ImageIO.createImageOutputStream(os);
                ImageIO.write(image, "PNG", imageOutput);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                multiEntity.addBinaryBody("file", is , ContentType.DEFAULT_BINARY ,outputFileName);
                is.close();
                os.close();
            }
            multiEntity.addTextBody("type", "1",ContentType.TEXT_PLAIN.withCharset("UTF-8"));
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
	
	//压缩图片
	private static BufferedImage resize(BufferedImage source, int targetW,  int targetH) {  
	       int type=source.getType();
	       BufferedImage target=null;
	       double sx=(double)targetW/source.getWidth();
	       double sy=(double)targetH/source.getHeight();
	       if(sx>sy || sx == 0){
	           sx=sy;
	           targetW=(int)(sx*source.getWidth());
	       }else{
	           sy=sx;
	           targetH=(int)(sy*source.getHeight());
	       }
	       if(type==BufferedImage.TYPE_CUSTOM){
	           ColorModel cm=source.getColorModel();
	                WritableRaster raster=cm.createCompatibleWritableRaster(targetW, targetH);
	                boolean alphaPremultiplied=cm.isAlphaPremultiplied();
	                target=new BufferedImage(cm,raster,alphaPremultiplied,null);
	       }else{
	           target=new BufferedImage(targetW, targetH,type);
	       }
	       Graphics2D g=target.createGraphics();
	       g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	       g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
	       g.dispose();
	       return target;
	   } 
	
}
